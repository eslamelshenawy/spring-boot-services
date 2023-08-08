package com.example.superheroservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "min1_avg")
public class SuperHero implements Serializable {

    @Id
    private String id;
    private String date;
    private String min1_C_A;
    private String min1_P_A;
}