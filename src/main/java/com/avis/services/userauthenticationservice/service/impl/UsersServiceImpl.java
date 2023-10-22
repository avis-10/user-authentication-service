package com.avis.services.userauthenticationservice.service.impl;

import com.avis.services.userauthenticationservice.model.CreateUserRequest;
import com.avis.services.userauthenticationservice.model.CreateUserResponse;
import com.avis.services.userauthenticationservice.security.repo.AuthUser;
import com.avis.services.userauthenticationservice.security.repo.AuthUserRepo;
import com.avis.services.userauthenticationservice.security.utils.JwtTokenUtil;
import com.avis.services.userauthenticationservice.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthUserRepo authUserRepo;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {

        validateNewUser(request);
        AuthUser newAuthuser = new AuthUser();
        newAuthuser.setUsername(request.getUserName());
        newAuthuser.setEncryptedPassword(generatedEncryptedPassword(request.getPassword()));
        AuthUser authUser = authUserRepo.save(newAuthuser);

        CreateUserResponse response = new CreateUserResponse(UUID.randomUUID(), request.getFirstName(),
                request.getLastName(), authUser.getUsername(), request.getEmailId());

        return response;
    }

    @Override
    public CreateUserResponse getUserById(UUID id) {
        return null;
    }

    @Override
    public CreateUserResponse getUserByUsername(String username) {
        CreateUserResponse response;
        Optional<AuthUser> optAuthuser = authUserRepo.findById(username);
        optAuthuser.ifPresentOrElse(authUser -> {
            // Need to implement this logic.
            // Add UUID column and other columns to authuser table
                },
                () -> { throw new UsernameNotFoundException("Username not found !"); });
    }

    private String generatedEncryptedPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    private void validateNewUser(CreateUserRequest request) {

        Optional<AuthUser> user = authUserRepo.findById(request.getUserName());
        user.ifPresent( user1 -> {
        throw new RuntimeException("UserName already in use : " + user1.getUsername());
        });
    }

}
