package com.murilonerdx.restapispring.controller;

import com.murilonerdx.restapispring.dto.PersonDTO;
import com.murilonerdx.restapispring.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = "PersonEndpoint")
@RestController
@RequestMapping("/api/person")
//@CrossOrigin
public class PersonController {

    @Autowired
    private PersonService service;

    @ApiOperation(value = "Find all persons")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<PersonDTO> findAll() {
        List<PersonDTO> persons = service.findAll();
        persons
                .forEach(p -> p.add(
                                linkTo(methodOn(BookController.class).findById(p.getId())).withSelfRel()
                        )
                );
        return persons;
    }

    @ApiOperation(value = "Find a specific person by your ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO findById(@PathVariable("id") Long id) {
        PersonDTO person = service.findById(id);
        person.add(linkTo(methodOn(PersonController.class).findAll()).withRel("List of the Persons"));
        return person;
    }

    @ApiOperation(value = "Create a new person")
    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO create(@RequestBody PersonDTO person) {
        return service.create(person);
    }

    @ApiOperation(value = "Update a specific person")
    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO update(@RequestBody PersonDTO person) {
        PersonDTO personDTO = service.update(person);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getId())).withSelfRel());
        return personDTO;
    }

    @ApiOperation(value = "Delete a specific person by your ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }


}
