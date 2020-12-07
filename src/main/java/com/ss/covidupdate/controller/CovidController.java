package com.ss.covidupdate.controller;

import com.ss.covidupdate.model.CountryDetails;
import com.ss.covidupdate.model.Summary;
import com.ss.covidupdate.service.CovidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@RestController
@RequestMapping("/covid")
public class CovidController {

    private static final Logger log = LoggerFactory.getLogger(CovidController.class);

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
    public ResponseEntity<?> getCasesByCountry(@PathVariable String countryCode) throws MalformedURLException, UnsupportedEncodingException {
        String cCode= URLDecoder.decode(countryCode, StandardCharsets.UTF_8.toString());;

        String[] arr = cCode.split("\\s+");
        return ResponseEntity.ok(arr[1] + " Active Cases " + covidService.getCasesByCountryId(arr[1]));
    }

    @RequestMapping(value = "/deaths/{countryCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getDeathByCountry(@PathVariable String countryCode) throws MalformedURLException, UnsupportedEncodingException {
        String cCode= URLDecoder.decode(countryCode, StandardCharsets.UTF_8.toString());;

        String[] arr = cCode.split("\\s+");
        return ResponseEntity.ok(arr[1] + " Death Cases " + covidService.getDeathByCountryId(arr[1]));
    }

    @RequestMapping(value = "/twilio/{countryCode}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> getDataForTwilio(@PathVariable String countryCode) throws MalformedURLException, UnsupportedEncodingException {
        String cCode= URLDecoder.decode(countryCode, StandardCharsets.UTF_8.toString());;
        String[] arr = cCode.split("\\s+");
        if(arr[0].equalsIgnoreCase("DEATHS")){
            String response = arr[1] + " Death Cases " + covidService.getDeathByCountryId(arr[1]);
            SmsController.sendMessage(response);
            return ResponseEntity.ok(response);
        }
        else if(arr[0].equalsIgnoreCase("CASES")){
            String response = arr[1] + " Active Cases " + covidService.getCasesByCountryId(arr[1]);
            SmsController.sendMessage(response);
            return ResponseEntity.ok(response);
        }else{
            SmsController.sendMessage("Invalid Country Code");
            return ResponseEntity.ok("Invalid Country Code");
        }
    }

    /**+
     * Twilio post call to fetch CASES by Country Code and Deaths By Country Code
     *
     * @param Body
     * @return String result
     * @throws MalformedURLException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/twilio/updates", method = RequestMethod.POST)
    public String postDataForTwilio(@RequestParam(name = "Body") String Body) throws MalformedURLException, UnsupportedEncodingException {
    log.info("Working whatsapp integration");
    String response = "Invalid Code";
    String cCode= URLDecoder.decode(Body, StandardCharsets.UTF_8.toString());;
    String[] arr = cCode.split("\\s+");
        if(arr[0].equalsIgnoreCase("DEATHS")){
            response = arr[1] + " Death Cases " + covidService.getDeathByCountryId(arr[1]);
            //SmsController.sendMessage(response);
            return response;
        }
        else if(arr[0].equalsIgnoreCase("CASES")){
            response = arr[1] + " Active Cases " + covidService.getCasesByCountryId(arr[1]);
            //SmsController.sendMessage(response);
            return response;
        }else{
            //SmsController.sendMessage("Invalid Country Code");
            return response;
        }
    }
}

