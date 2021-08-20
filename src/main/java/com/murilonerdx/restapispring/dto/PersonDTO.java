package com.murilonerdx.restapispring.dto;

import com.github.dozermapper.core.Mapping;
import lombok.Data;

import org.springframework.hateoas.RepresentationModel;

@Data
public class PersonDTO extends RepresentationModel<PersonDTO> {
    @Mapping("id")
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
}
