package com.example.superheroservice.controller;


import com.example.superheroservice.dto.DateRangeRequest;
import com.example.superheroservice.dto.ResponseDate;
import com.example.superheroservice.entity.SuperHero;
import com.example.superheroservice.response.Response;
import com.example.superheroservice.response.ResponseM;
import com.example.superheroservice.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/")
public class SuperHeroController {

    @Autowired
    private SuperHeroService superHeroService;

    @GetMapping("/date")
    public ResponseEntity<Response<List<List<String>>>> getDataByDateRange(@RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo) throws ParseException {
         return superHeroService.findByDateBetween(dateFrom, dateTo);
    }

    @GetMapping("dates")
    public ResponseEntity<Response<List<ResponseDate>>> getDataByDateRangeWithoutChangesInDate(@RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo) throws ParseException {
         return superHeroService.getDataByDateRangeWithoutChangesInDate(dateFrom, dateTo);
    }

    @PostMapping("rang/dates")
    public ResponseEntity<List<ResponseM<List<String>>>> getDataByDateRangePostRequest(@RequestBody List<DateRangeRequest> dateRanges) throws ParseException {
         return superHeroService.getDataByDateRangePostRequest(dateRanges);
    }

    @PostMapping("rang/dates/all")
    public ResponseEntity<List<ResponseM<List<ResponseDate>>>> getDataByDateRangeWithoutChangesInDatePostRequest(@RequestBody List<DateRangeRequest> dateRanges) throws ParseException {
         return superHeroService.getDataByDateRangeWithoutChangesInDatePostRequest(dateRanges);
    }

}
