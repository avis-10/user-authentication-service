package com.avis.services.userauthenticationservice.service;

import com.avis.services.userauthenticationservice.model.CreateUserRequest;
import com.avis.services.userauthenticationservice.model.CreateUserResponse;

import java.util.UUID;

public interface UsersService {

    CreateUserResponse createUser(CreateUserRequest request);

    CreateUserResponse getUserById(UUID id);

    CreateUserResponse getUserByUsername(String username);
}
