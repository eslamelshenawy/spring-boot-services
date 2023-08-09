package com.example.superheroservice.dto;


import lombok.*;
import org.bson.types.Decimal128;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseDate {

    @Field(name = "DATE")
    private List<Date> dates;
    @Field(name = "min1_C_A")
    private List<Decimal128> minCA;
    @Field(name = "min1_P_A")
    private List<Decimal128> minPA;



}
