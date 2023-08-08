package com.example.superheroservice.repository;

import com.example.superheroservice.entity.SuperHero;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SuperHeroRepository extends MongoRepository<SuperHero, String> {

}
