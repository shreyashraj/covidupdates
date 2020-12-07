package com.ss.covidupdate.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Summary {

    @JsonProperty("Message")
    String Message;
    @JsonProperty("Global")
    Global Global;
    @JsonProperty("Countries")
    List<Countries> Countries;
    Date Date;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public com.ss.covidupdate.model.Global getGlobal() {
        return Global;
    }

    public void setGlobal(com.ss.covidupdate.model.Global global) {
        Global = global;
    }

    public List<com.ss.covidupdate.model.Countries> getCountries() {
        return Countries;
    }

    public void setCountries(List<com.ss.covidupdate.model.Countries> countries) {
        Countries = countries;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "Message='" + Message + '\'' +
                ", Global=" + Global +
                ", Countries=" + Countries +
                ", Date=" + Date +
                '}';
    }
}
