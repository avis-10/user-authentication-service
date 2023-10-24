package com.avis.services.userauthenticationservice.service.impl;

import com.avis.services.userauthenticationservice.model.CreateUserRequest;
import com.avis.services.userauthenticationservice.model.CreateUserResponse;
import com.avis.services.userauthenticationservice.security.models.JwtRequest;
import com.avis.services.userauthenticationservice.security.models.JwtResponse;
import com.avis.services.userauthenticationservice.security.repo.AuthUser;
import com.avis.services.userauthenticationservice.security.repo.AuthUserRepo;
import com.avis.services.userauthenticationservice.security.service.JwtUserDetailsService;
import com.avis.services.userauthenticationservice.security.utils.JwtTokenUtil;
import com.avis.services.userauthenticationservice.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {

        validateNewUser(request);
        AuthUser newAuthuser = new AuthUser();
        newAuthuser.setUsername(request.getUserName());
        newAuthuser.setEncryptedPassword(generateEncryptedPassword(request.getPassword()));
        newAuthuser.setUuid(UUID.randomUUID().toString());
        newAuthuser.setFirstName(request.getFirstName());
        newAuthuser.setLastName(request.getLastName());
        newAuthuser.setEmailId(request.getEmailId());
        AuthUser authUser = authUserRepo.save(newAuthuser);

        CreateUserResponse response = new CreateUserResponse(authUser.getUuid(), authUser.getFirstName(),
                authUser.getLastName(), authUser.getUsername(), authUser.getEmailId());

        return response;
    }

    @Override
    public JwtResponse loginUser(JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }

    @Override
    public CreateUserResponse getUserByUuid(String id) {
        Optional<AuthUser> authUser = authUserRepo.findByUuid(id);
        if (authUser.isPresent()) {
            return buildResponse(authUser.get());
        } else throw new UsernameNotFoundException("User with given uuid not found !");
    }

    @Override
    public CreateUserResponse getUserByUsername(String username) {
        Optional<AuthUser> authUser = authUserRepo.findById(username);
        if (authUser.isPresent()) {
            return buildResponse(authUser.get());
        } else throw new UsernameNotFoundException("Username not found !");
    }

    private String generateEncryptedPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    private void validateNewUser(CreateUserRequest request) {

        Optional<AuthUser> user = authUserRepo.findById(request.getUserName());
        user.ifPresent( user1 -> {
        throw new RuntimeException("UserName already in use : " + user1.getUsername());
        });
    }

    private CreateUserResponse buildResponse(AuthUser user) {
        CreateUserResponse response = null;
        return response.builder()
                .uuid(user.getUuid()).firstName(user.getFirstName())
                .lastName(user.getLastName()).userName(user.getUsername())
                .emailId(user.getEmailId()).build();
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
