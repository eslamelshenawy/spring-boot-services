package com.example.superheroservice.service;

import com.example.superheroservice.entity.SuperHero;
import com.example.superheroservice.repository.SuperHeroRepository;
import com.example.superheroservice.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SuperHeroService {

    @Autowired
    private SuperHeroRepository repository;

    public ResponseEntity<Response<List<SuperHero>>> getAll() {
        List<SuperHero> organizations = repository.findAll();
        Response<List<SuperHero>> response = Response.<List<SuperHero>>builder().data(organizations).ResponseMessage("all").ResponseCode(200).build();
        return ResponseEntity.ok(response);

    }
}
