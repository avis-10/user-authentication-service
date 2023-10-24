package com.avis.services.userauthenticationservice.controller;

import com.avis.services.userauthenticationservice.model.CreateUserRequest;
import com.avis.services.userauthenticationservice.model.CreateUserResponse;
import com.avis.services.userauthenticationservice.security.models.JwtRequest;
import com.avis.services.userauthenticationservice.security.models.JwtResponse;
import com.avis.services.userauthenticationservice.security.service.JwtUserDetailsService;
import com.avis.services.userauthenticationservice.security.utils.JwtTokenUtil;
import com.avis.services.userauthenticationservice.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UsersService usersService;

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse response = usersService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        JwtResponse response = usersService.loginUser(authenticationRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/uuid/{id}")
    public ResponseEntity<CreateUserResponse> findUserByUuid(@PathVariable("id") String id) {
        return new ResponseEntity<>(usersService.getUserByUuid(id), HttpStatus.FOUND);
    }

    @GetMapping("/username/{name}")
    public ResponseEntity<CreateUserResponse> findUserByUsername(@PathVariable("name") String name) {
        return new ResponseEntity<>(usersService.getUserByUsername(name), HttpStatus.FOUND);
    }
}
