package com.avis.services.userauthenticationservice.service;

import com.avis.services.userauthenticationservice.model.CreateUserRequest;
import com.avis.services.userauthenticationservice.model.CreateUserResponse;

public interface UsersService {

    CreateUserResponse createUser(CreateUserRequest request);

    CreateUserResponse getUserByUuid(String id);

    CreateUserResponse getUserByUsername(String username);
}
