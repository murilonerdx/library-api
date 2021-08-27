package com.murilonerdx.restapispring.service;

import com.murilonerdx.restapispring.dto.BookDTO;
import com.murilonerdx.restapispring.exceptions.ResourceNotFoundException;
import com.murilonerdx.restapispring.model.Book;
import com.murilonerdx.restapispring.repository.BookRepository;
import com.murilonerdx.restapispring.repository.UserRepository;
import com.murilonerdx.restapispring.util.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    final
    UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var user = repository.findByUsername(s);
        if(user != null){
            return user;
        }else{
            throw new UsernameNotFoundException("Username " + s + " not found");
        }

    }
}
