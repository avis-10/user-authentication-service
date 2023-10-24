package com.avis.services.userauthenticationservice.service;

import com.avis.services.userauthenticationservice.model.CreateUserRequest;
import com.avis.services.userauthenticationservice.model.CreateUserResponse;
import com.avis.services.userauthenticationservice.security.models.JwtRequest;
import com.avis.services.userauthenticationservice.security.models.JwtResponse;

public interface UsersService {

    CreateUserResponse createUser(CreateUserRequest request);

    JwtResponse loginUser(JwtRequest authenticationRequest) throws Exception;

    CreateUserResponse getUserByUuid(String id);

    CreateUserResponse getUserByUsername(String username);
}
