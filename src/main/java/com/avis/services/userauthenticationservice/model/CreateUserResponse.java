package com.avis.services.userauthenticationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class CreateUserResponse {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String emailId;
}
