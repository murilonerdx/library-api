package com.murilonerdx.restapispring.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO extends RepresentationModel<PersonDTO> {
    @Mapping("id")
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
}
