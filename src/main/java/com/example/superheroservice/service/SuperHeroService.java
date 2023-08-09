package com.example.superheroservice.service;

import com.example.superheroservice.dto.ResponseDate;
import com.example.superheroservice.entity.SuperHero;
import com.example.superheroservice.repository.SuperHeroRepository;
import com.example.superheroservice.response.Response;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import lombok.RequiredArgsConstructor;
import org.bson.types.Decimal128;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SuperHeroService {

    @Autowired
    private SuperHeroRepository superHeroRepository;

    public ResponseEntity<Response<List<ResponseDate>>> findByDateBetween(String dateFrom, String dateTo) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date fromDate = dateFormat.parse(dateFrom);
        Date toDate = dateFormat.parse(dateTo);

        List<ResponseDate> data = superHeroRepository.findByDateBetween(fromDate, toDate);

        List<String> Dates = data.stream().map(d -> d.getDates().stream()
                .map(date -> date.toString()).collect(Collectors.joining(", "))).collect(Collectors.toList());

        List<String> minCa2 = data.stream().map(d -> d.getMinCA().stream()
                .map(min_ca2 -> min_ca2.toString()).collect(Collectors.joining(", "))).collect(Collectors.toList());

        List<String> minPA = data.stream().map(d -> d.getMinPA().stream()
                .map(min_pa -> min_pa.toString()).collect(Collectors.joining(", "))).collect(Collectors.toList());

        List<Response.Source> results = Arrays.asList(new Response.Source(Dates, convert(minCa2), convert(minPA)));
        Response<List<ResponseDate>> response = Response.<List<ResponseDate>>builder().source(results).build();
        return ResponseEntity.ok(response);
    }

    public List<Decimal128> convert(List<String> data) {
        Decimal128[] decimal128MinCAs = new Decimal128[data.size()];
        for (int i = 0; i < data.size(); i++) {
            decimal128MinCAs[i] = Decimal128.parse(data.get(i));
        }
        List<Decimal128> decimal128List = Arrays.asList(decimal128MinCAs);

        return decimal128List;
    }
}
