package com.example.superheroservice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.bson.types.Decimal128;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> implements Serializable {
    private T source;

//    private List<Source> source;
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Getter
//    @Setter
//    @Builder
//    public static class Source {
//        private List<String>  dates;
//        private List<Decimal128> minCA;
//        private List<Decimal128> minPA;
//    }
}
