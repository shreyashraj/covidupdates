package com.ss.covidupdate.controller;

import com.ss.covidupdate.model.CountryDetails;
import com.ss.covidupdate.model.Summary;
import com.ss.covidupdate.service.CovidService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/covid")
public class CovidController {

    @Autowired
    private CovidService covidService;

    @RequestMapping(value = "/getCountry", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getCountry() throws MalformedURLException {
        List<CountryDetails> result = covidService.getCountryCode();
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/getSummary", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getSummary() throws MalformedURLException {
        List<Summary> result = covidService.getGlobalData();
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/total/active/cases", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getTotalActiveCases() throws MalformedURLException {
        return ResponseEntity.ok("Total Active Cases " + covidService.getTotalActiveCases());
    }

    @RequestMapping(value = "/total/death", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getTotalDeath() throws MalformedURLException {
        return ResponseEntity.ok("Total Death " + covidService.getTotalDeath()  );
    }

    @RequestMapping(value = "/cases/{countryCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getCasesByCountry(@PathVariable String countryCode) throws MalformedURLException {
        String[] arr = countryCode.split("\\s+");
        return ResponseEntity.ok(arr[1] + " Active Cases " + covidService.getCasesByCountryId(arr[1]));
    }

    @RequestMapping(value = "/deaths/{countryCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getDeathByCountry(@PathVariable String countryCode) throws MalformedURLException {
        String[] arr = countryCode.split("\\s+");
        return ResponseEntity.ok(arr[1] + " Death Cases " + covidService.getDeathByCountryId(arr[1]));
    }

    @RequestMapping(value = "/twilio/{countryCode}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> getDataForTwilio(@PathVariable String countryCode) throws MalformedURLException {
        String[] arr = countryCode.split("\\s+");
        if(arr[0].equalsIgnoreCase("DEATHS")){
            return ResponseEntity.ok(arr[1] + " Death Cases " + covidService.getDeathByCountryId(arr[1]));
        }
        else if(arr[0].equalsIgnoreCase("CASES")){
            return ResponseEntity.ok(arr[1] + " Active Cases " + covidService.getCasesByCountryId(arr[1]));
        }else{
            return ResponseEntity.ok("Invalid Country Code");
        }

    }

}

