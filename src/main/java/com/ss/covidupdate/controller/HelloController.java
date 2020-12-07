package com.ss.covidupdate.controller;

import com.ss.covidupdate.model.CountryDetails;
import com.ss.covidupdate.model.Summary;
import com.ss.covidupdate.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {

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
        return ResponseEntity.ok(countryCode + " Active Cases " + covidService.getCasesByCountryId(countryCode));
    }
}

