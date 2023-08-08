package com.example.superheroservice.entity;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "min1_avg")
public class SuperHero implements Serializable {

    @Id
    private String id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date DATE;
    private String min1_C_A;
    private String min1_P_A;
}