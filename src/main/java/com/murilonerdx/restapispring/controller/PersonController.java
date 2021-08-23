package com.murilonerdx.restapispring.controller;

import com.murilonerdx.restapispring.dto.PersonDTO;
import com.murilonerdx.restapispring.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

    @ApiOperation(value = "Find all people" )
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findAll(
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="limit", defaultValue = "12") int limit,
            @RequestParam(value="direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonDTO> persons =  service.findAll(pageable);
        persons
                .stream()
                .forEach(p -> p.add(
                                linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()
                        )
                );


        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @ApiOperation(value = "Find a specific person by name" )
    @GetMapping(value = "/findPersonByName/{firstName}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findPersonByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="limit", defaultValue = "12") int limit,
            @RequestParam(value="direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonDTO> persons =  service.findPersonByName(firstName, pageable);
        persons
                .stream()
                .forEach(p -> p.add(
                                linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()
                        )
                );

        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @ApiOperation(value = "Find a specific person by your ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO findById(@PathVariable("id") Long id) {
        PersonDTO person = service.findById(id);
        person.add(linkTo(methodOn(PersonController.class).findAll(0,12, "asc")).withRel("List of the Persons"));
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
