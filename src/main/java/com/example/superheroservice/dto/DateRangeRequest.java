package com.example.superheroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DateRangeRequest {

    private String dateFrom;
    private String dateTo;

}
