package com.murilonerdx.restapispring.controller;

import com.murilonerdx.restapispring.dto.BookDTO;
import com.murilonerdx.restapispring.exceptions.InvalidJwtAuthenticationException;
import com.murilonerdx.restapispring.model.User;
import com.murilonerdx.restapispring.repository.UserRepository;
import com.murilonerdx.restapispring.security.AccountCredentialDTO;
import com.murilonerdx.restapispring.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/auth")
@Api(tags="AuthEndpoint")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository repository;

    @ApiOperation(value = "Authenticate a user by credentials")
    @SuppressWarnings("rawtypes")
    @PostMapping(value= "/signin",produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> create(@RequestBody AccountCredentialDTO accountCredentialDTO) {
        try{
            String username = accountCredentialDTO.getUsername();
            String password = accountCredentialDTO.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = repository.findByUsername(username);

            String token = "";

            if(user !=null){
                token = tokenProvider.createToken(username, user.getRoles());
            }else{
                throw new UsernameNotFoundException("Username " + username + " not found");
            }

            Map<Object, Object> model = new HashMap<Object, Object>();
            model.put("username", username);
            model.put("token",token);
            return ResponseEntity.ok(model);
        }catch(AuthenticationException e){
            throw new BadCredentialsException("Invalid username/passowrd supplied");
        }
    }
}
