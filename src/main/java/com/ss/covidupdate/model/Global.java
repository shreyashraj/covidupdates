package com.ss.covidupdate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Global {
    long NewConfirmed;
    long TotalConfirmed;
    long NewDeaths;
    long TotalDeaths;
    long NewRecovered;
    long TotalRecovered;

    public long getNewConfirmed() {
        return NewConfirmed;
    }

    public void setNewConfirmed(long newConfirmed) {
        NewConfirmed = newConfirmed;
    }

    public long getTotalConfirmed() {
        return TotalConfirmed;
    }

    public void setTotalConfirmed(long totalConfirmed) {
        TotalConfirmed = totalConfirmed;
    }

    public long getNewDeaths() {
        return NewDeaths;
    }

    public void setNewDeaths(long newDeaths) {
        NewDeaths = newDeaths;
    }

    public long getTotalDeaths() {
        return TotalDeaths;
    }

    public void setTotalDeaths(long totalDeaths) {
        TotalDeaths = totalDeaths;
    }

    public long getNewRecovered() {
        return NewRecovered;
    }

    public void setNewRecovered(long newRecovered) {
        NewRecovered = newRecovered;
    }

    public long getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalRecovered(long totalRecovered) {
        TotalRecovered = totalRecovered;
    }

    @Override
    public String toString() {
        return "Global{" +
                "NewConfirmed=" + NewConfirmed +
                ", TotalConfirmed=" + TotalConfirmed +
                ", NewDeaths=" + NewDeaths +
                ", TotalDeaths=" + TotalDeaths +
                ", NewRecovered=" + NewRecovered +
                ", TotalRecovered=" + TotalRecovered +
                '}';
    }
}