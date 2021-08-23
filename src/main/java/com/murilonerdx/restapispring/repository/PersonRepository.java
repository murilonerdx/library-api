package com.murilonerdx.restapispring.repository;

import com.murilonerdx.restapispring.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {


    @Query("SELECT p FROM Person p WHERE p.firstName LIKE :firstName")
    Page<Person> findPersonByName(@Param("firstName") String firstName, Pageable pageable);
}
