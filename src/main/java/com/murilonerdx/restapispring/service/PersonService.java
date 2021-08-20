package com.murilonerdx.restapispring.service;

import com.murilonerdx.restapispring.dto.PersonDTO;
import com.murilonerdx.restapispring.exceptions.ResourceNotFoundException;
import com.murilonerdx.restapispring.model.Person;
import com.murilonerdx.restapispring.repository.PersonRepository;
import com.murilonerdx.restapispring.util.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    final
    PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonDTO create(PersonDTO person) {
        var entity = DozerConverter.parseObject(person, Person.class);
        return DozerConverter.parseObject(repository.save(entity), PersonDTO.class);
    }

    public List<PersonDTO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        var entity =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var entityDTO = repository.save(entity);
        return DozerConverter.parseObject(entityDTO, PersonDTO.class);
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}
