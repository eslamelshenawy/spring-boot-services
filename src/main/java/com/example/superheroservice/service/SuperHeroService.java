package com.example.superheroservice.service;

import com.example.superheroservice.dto.DateRangeRequest;
import com.example.superheroservice.dto.ResponseDate;
import com.example.superheroservice.repository.SuperHeroRepository;
import com.example.superheroservice.response.Response;
import com.example.superheroservice.response.ResponseM;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public ResponseEntity<Response<List<List<String>>>> findByDateBetween(String dateFrom, String dateTo) throws ParseException {

        Date fromDate = dateFormat.parse(dateFrom);
        Date toDate = dateFormat.parse(dateTo);
        List<ResponseDate> data = superHeroRepository.findByDateBetween(fromDate, toDate);

        List<List<String>> results = getResult(data);

        Response<List<List<String>>> response = Response.<List<List<String>>>builder().source(results).build();

        return ResponseEntity.ok(response);
    }


    public ResponseEntity<Response<List<ResponseDate>>> getDataByDateRangeWithoutChangesInDate(String dateFrom, String dateTo) throws ParseException {

        Date fromDate = dateFormat.parse(dateFrom);
        Date toDate = dateFormat.parse(dateTo);

        List<ResponseDate> data = superHeroRepository.findByDateBetween(fromDate, toDate);

        Response<List<ResponseDate>> response = Response.<List<ResponseDate>>builder().source(data).build();
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<ResponseM<List<String>>>> getDataByDateRangePostRequest(List<DateRangeRequest> dateRanges) throws ParseException {

        List<ResponseM<List<String>>> result = new ArrayList<>();

        for (DateRangeRequest dateRange : dateRanges) {
            Date dateFrom = dateRange.getDateFrom();
            Date dateTo = dateRange.getDateTo();

            List<ResponseDate> data = superHeroRepository.findByDateBetween(dateFrom, dateTo);

            List<List<String>> results = getResult(data);
            ResponseM<List<String>> response = ResponseM.<List<String>>builder().source(results).build();

            result.add(response);
        }

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<List<ResponseM<List<ResponseDate>>>> getDataByDateRangeWithoutChangesInDatePostRequest(List<DateRangeRequest> dateRanges) throws ParseException {

        List<ResponseM<List<ResponseDate>>> result = new ArrayList<>();

        for (DateRangeRequest dateRange : dateRanges) {
            Date dateFrom = dateRange.getDateFrom();
            Date dateTo = dateRange.getDateTo();

            List<ResponseDate> data = superHeroRepository.findByDateBetween(dateFrom, dateTo);
            List<List<ResponseDate>> results = data.stream()
                    .map(responseDate -> Arrays.asList(responseDate))
                    .collect(Collectors.toList());

            ResponseM<List<ResponseDate>> response = ResponseM.<List<ResponseDate>>builder().source(results).build();

            result.add(response);
        }

        return ResponseEntity.ok(result);
    }


    public List<List<String>> getResult(List<ResponseDate> data) {

        List<String> Dates = data.stream().map(d -> d.getDates().stream().map(date -> date.toString()).collect(Collectors.joining(", "))).collect(Collectors.toList());

        List<String> minCa2 = data.stream().map(d -> d.getMinCA().stream().map(min_ca2 -> min_ca2.toString()).collect(Collectors.joining(", "))).collect(Collectors.toList());

        List<String> minPA = data.stream().map(d -> d.getMinPA().stream().map(min_pa -> min_pa.toString()).collect(Collectors.joining(", "))).collect(Collectors.toList());

        List<List<String>> results = Arrays.asList(Dates, minCa2, minPA);

        return results;
    }
}
