package com.example.superheroservice.entity;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @Field(name = "DATE")
    private Date date;
    @Field(name = "min1_C_A" , targetType = FieldType.DECIMAL128)
    private Decimal128 minCA;
    @Field(name = "min1_P_A" , targetType = FieldType.DECIMAL128)
    private Decimal128 minPA;
}