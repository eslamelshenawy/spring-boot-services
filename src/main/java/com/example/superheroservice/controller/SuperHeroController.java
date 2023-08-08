package com.example.superheroservice.controller;


import com.example.superheroservice.entity.SuperHero;
import com.example.superheroservice.response.Response;
import com.example.superheroservice.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/super-hero")
public class SuperHeroController {

    @Autowired
    private SuperHeroService superHeroService;


    @GetMapping
    public ResponseEntity<Response<List<SuperHero>>> getAll() {
        return superHeroService.getAll();
    }
}
