package com.ss.covidupdate.service;

import com.google.gson.Gson;
import com.ss.covidupdate.CovidUpdateApplication;
import com.ss.covidupdate.model.Countries;
import com.ss.covidupdate.model.CountryDetails;
import com.ss.covidupdate.model.Global;
import com.ss.covidupdate.model.Summary;
import com.ss.covidupdate.repository.CountryRepository;
import com.ss.covidupdate.repository.GlobalData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CovidService {
    private static final Logger log = LoggerFactory.getLogger(CovidService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    GlobalData globalData;

    /**+
     * Get all the country details
     *
     * @return CountryDetails List
     * @throws MalformedURLException
     */
    public List<CountryDetails> getCountryCode() throws MalformedURLException {
    String URL = "https://api.covid19api.com/countries";
    ResponseEntity<String> countries = restTemplate.getForEntity(URL, String.class);
    Gson json = new Gson();
    CountryDetails[] c = json.fromJson(countries.getBody(), CountryDetails[].class);
    System.out.println(Arrays.asList(c));
    return Arrays.asList(c);
    }

    /**+
     * Fetch all the data using scheduler
     *
     * @return List of Data from Summary call
     * @throws MalformedURLException
     */
    public List<Summary> getGlobalData() throws MalformedURLException {
        log.info("Calling scheduled getSummary");
        String URL1 = "https://api.covid19api.com/summary";
        ResponseEntity<String> countries = restTemplate.getForEntity(URL1, String.class);
        Gson json = new Gson();
        Summary c = json.fromJson(countries.getBody(), Summary.class);
        //System.out.println(Arrays.asList(c));
        List<Countries> countryDetails = c.getCountries();
        if(countryRepository.findAll() != null){
            log.info("Deleting Old Data");
            countryRepository.deleteAll();
            globalData.deleteAll();
        }
        log.info("Updating new Data");
        countryRepository.saveAll(countryDetails);
        globalData.save(c.getGlobal());
        return Arrays.asList(c);
    }


    /**+
     * Get all the cases by Country ID
     *
     * @param countryCode
     * @return totalCases
     */
    public long getCasesByCountryId(String countryCode){
       Optional<Countries> countryDetails = countryRepository.findByCountryCode(countryCode);
       Countries countryDetails1 = countryDetails.get();
       return countryDetails1.getTotalConfirmed();
    }


    /**+
     * Get all the deaths by Country ID
     *
     * @param countryCode
     * @return totalDeaths
     */
    public long getDeathByCountryId(String countryCode){
        Optional<Countries> countryDetails = countryRepository.findByCountryCode( countryCode);
        Countries countryDetails1 = countryDetails.get();
        return countryDetails1.getTotalDeaths();
    }


    /**+
     * Get all the active cases globally
     *
     * @return totalActiveCases
     */
    public long getTotalActiveCases(){
       Global global  = (Global) globalData.findAll().get(0);
       return global.getTotalConfirmed();
    }

    /**+
     * Get all the deaths globally
     *
     * @return totalDeaths
     */
    public long getTotalDeath(){
        Global global  = (Global) globalData.findAll().get(0);
        return global.getTotalDeaths();
    }
}
