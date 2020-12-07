package com.ss.covidupdate.repository;

import com.ss.covidupdate.model.Countries;
import com.ss.covidupdate.model.CountryDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends MongoRepository<Countries, String> {
    @Query("{'CountryCode' : ?0}")
    Optional<Countries> findByCountryCode(String countryCode);


}
