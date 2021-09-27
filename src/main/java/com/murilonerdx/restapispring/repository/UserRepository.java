package com.murilonerdx.restapispring.repository;

import com.murilonerdx.restapispring.model.Person;
import com.murilonerdx.restapispring.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query("SELECT u FROM User u WHERE u.username =:username")
    User findByUsername(@Param("username") String username);
}
