package com.murilonerdx.restapispring.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
public class PersonDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
}
