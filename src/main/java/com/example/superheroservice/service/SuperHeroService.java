package com.example.superheroservice.service;

import com.example.superheroservice.dto.ResponseDate;
import com.example.superheroservice.repository.SuperHeroRepository;
import com.example.superheroservice.response.Response;
import lombok.RequiredArgsConstructor;
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
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public ResponseEntity<Response<List<String>>> findByDateBetween(String dateFrom, String dateTo) throws ParseException {

        Date fromDate = dateFormat.parse(dateFrom);
        Date toDate = dateFormat.parse(dateTo);
        List<ResponseDate> data = superHeroRepository.findByDateBetween(fromDate, toDate);

        List<List<String>> results = getResult(data);

//    Response<List<List<String>>> response = Response.<List<List<String>>>builder().source(results).build();
        Response<List<String>> response = Response.<List<String>>builder().source(
                results.stream().map(r -> r.toString().replaceAll("[\\[\\]\\\\]", "")
                ).collect(Collectors.toList())).build();

        return ResponseEntity.ok(response);
    }


    public ResponseEntity<Response<List<ResponseDate>>> getDataByDateRangeWithoutChangesInDate(String dateFrom, String dateTo) throws ParseException {

        Date fromDate = dateFormat.parse(dateFrom);
        Date toDate = dateFormat.parse(dateTo);

        List<ResponseDate> data = superHeroRepository.findByDateBetween(fromDate, toDate);

        Response<List<ResponseDate>> response = Response.<List<ResponseDate>>builder().source(data).build();
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response<List<ResponseDate>>> getDataByDateRangeWithoutChangesInDates(String dateFrom, String dateTo) throws ParseException {
//        2023-05-31T22:00:00.000Z
        Date fromDate = dateFormat.parse(dateFrom);
        Date toDate = dateFormat.parse(dateTo);

        List<ResponseDate> data = superHeroRepository.findByDateBetween(fromDate, toDate);

        Response<List<ResponseDate>> response = Response.<List<ResponseDate>>builder().source(data).build();
        return ResponseEntity.ok(response);
    }


    public List<List<String>> getResult(List<ResponseDate> data) {

        List<String> Dates = data.stream().map(d -> d.getDates().stream().map(date -> date.toString()).collect(Collectors.joining(", "))).collect(Collectors.toList());

        List<String> minCa2 = data.stream().map(d -> d.getMinCA().stream().map(min_ca2 -> min_ca2.toString()).collect(Collectors.joining(", "))).collect(Collectors.toList());

        List<String> minPA = data.stream().map(d -> d.getMinPA().stream().map(min_pa -> min_pa.toString()).collect(Collectors.joining(", "))).collect(Collectors.toList());

        List<List<String>> results = Arrays.asList(Dates, minCa2, minPA);

        return results;
    }
}
