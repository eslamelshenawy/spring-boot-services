package com.example.superheroservice.repository;

import com.example.superheroservice.dto.ResponseDate;
import com.example.superheroservice.entity.SuperHero;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;


public interface SuperHeroRepository extends MongoRepository<SuperHero, String> {
    List<ResponseDate> findByDateBetween(Date fromDate, Date toDate);
}
