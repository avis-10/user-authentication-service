package com.avis.services.userauthenticationservice.controller;

import com.avis.services.userauthenticationservice.model.CreateUserRequest;
import com.avis.services.userauthenticationservice.model.CreateUserResponse;
import com.avis.services.userauthenticationservice.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse response = usersService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
