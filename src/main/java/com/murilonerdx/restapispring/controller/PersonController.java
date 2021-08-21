package com.murilonerdx.restapispring.controller;

import com.murilonerdx.restapispring.dto.PersonDTO;
import com.murilonerdx.restapispring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public List<PersonDTO> findAll() {
        List<PersonDTO> persons = service.findAll();
        for(PersonDTO pdto : persons){
            Long id = pdto.getId();
            pdto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        }
        return persons;
    }

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public PersonDTO findById(@PathVariable("id") Long id) {
        PersonDTO person = service.findById(id);
        person.add(linkTo(methodOn(PersonController.class).findAll()).withRel("List of the Persons"));
        return person;
    }

    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PersonDTO create(@RequestBody PersonDTO person) {
        return service.create(person);
    }

    @PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PersonDTO update(@RequestBody PersonDTO person) {
        PersonDTO personDTO = service.update(person);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getId())).withSelfRel());
        return personDTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }


}
