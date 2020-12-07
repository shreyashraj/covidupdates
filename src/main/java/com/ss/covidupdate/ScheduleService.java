package com.ss.covidupdate;

import com.ss.covidupdate.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.MalformedURLException;

@Configuration
public class ScheduleService {

    @Autowired
    private CovidService covidService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void scheduleFetchData() throws MalformedURLException {
        covidService.getGlobalData();
    }

}
