package com.murilonerdx.restapispring.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="first_name", nullable=false)
    private String firstName;
    @Column(name="last_name", nullable=false)
    private String lastName;
    @Column(nullable=false, length = 100)
    private String address;
    @Column(nullable=false, length = 6)
    private String gender;
}
