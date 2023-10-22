package com.avis.services.userauthenticationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class CreateUserResponse {
    private String uuid;
    private String firstName;
    private String lastName;
    private String userName;
    private String emailId;
}
