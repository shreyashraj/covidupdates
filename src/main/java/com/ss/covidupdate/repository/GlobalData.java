package com.ss.covidupdate.repository;

import com.ss.covidupdate.model.Global;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalData extends MongoRepository<Global, String> {
}
