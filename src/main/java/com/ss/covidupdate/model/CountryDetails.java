package com.ss.covidupdate.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class        CountryDetails {

    String Country;
    String Slug;
    String ISO2;

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getSlug() {
        return Slug;
    }

    public void setSlug(String slug) {
        Slug = slug;
    }

    public String getISO2() {
        return ISO2;
    }

    public void setISO2(String ISO2) {
        this.ISO2 = ISO2;
    }

    @Override
    public String toString() {
        return "CountryDetails{" +
                "Country='" + Country + '\'' +
                ", Slug='" + Slug + '\'' +
                ", ISO2='" + ISO2 + '\'' +
                '}';
    }
}
