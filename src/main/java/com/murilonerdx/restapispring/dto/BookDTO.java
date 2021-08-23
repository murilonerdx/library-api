package com.murilonerdx.restapispring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO extends RepresentationModel implements Serializable {
    @Mapping("id")
    private Long id;
    private String author;
    private Date launchDate;
    private Double price;
    private String title;
}