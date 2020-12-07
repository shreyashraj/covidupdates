package com.ss.covidupdate.service;

import com.google.gson.Gson;
import com.ss.covidupdate.model.Countries;
import com.ss.covidupdate.model.CountryDetails;
import com.ss.covidupdate.model.Global;
import com.ss.covidupdate.model.Summary;
import com.ss.covidupdate.repository.CountryRepository;
import com.ss.covidupdate.repository.GlobalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CovidService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    GlobalData globalData;

    public List<CountryDetails> getCountryCode() throws MalformedURLException {
    String URL = "https://api.covid19api.com/countries";
    ResponseEntity<String> countries = restTemplate.getForEntity(URL, String.class);
    Gson json = new Gson();
    CountryDetails[] c = json.fromJson(countries.getBody(), CountryDetails[].class);
    System.out.println(Arrays.asList(c));
    return Arrays.asList(c);
    }

    public List<Summary> getGlobalData() throws MalformedURLException {
        //String URL = "https://api.covid19api.com/countries";
        String URL1 = "https://api.covid19api.com/summary";
        ResponseEntity<String> countries = restTemplate.getForEntity(URL1, String.class);
        Gson json = new Gson();
        Summary c = json.fromJson(countries.getBody(), Summary.class);
        //System.out.println(Arrays.asList(c));
        List<Countries> countryDetails = c.getCountries();
        countryRepository.saveAll(countryDetails);
        globalData.save(c.getGlobal());
        return Arrays.asList(c);
    }

    public long getCasesByCountryId(String countryCode){
        Optional<Countries> countryDetails = countryRepository.findByCountryCode(countryCode);
        Countries countryDetails1 = countryDetails.get();
        return countryDetails1.getTotalConfirmed();
        }


    public long getTotalActiveCases(){
       Global global  = (Global) globalData.findAll().get(0);
       return global.getTotalConfirmed();
    }

    public long getTotalDeath(){
        Global global  = (Global) globalData.findAll().get(0);
        return global.getTotalDeaths();
    }
}
