package com.ss.covidupdate.repository;

import com.ss.covidupdate.model.Countries;
import com.ss.covidupdate.model.CountryDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends MongoRepository<Countries, String> {
    Optional<Countries> findByCountryCode(String countryCode);

}
