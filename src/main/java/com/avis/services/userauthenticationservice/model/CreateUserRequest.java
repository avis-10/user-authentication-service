package com.avis.services.userauthenticationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserRequest {
    @NotBlank
    private String firstName;
    private String lastName;

    @NotNull
    private String userName;

    @Email
    private String emailId;

    @NotNull
    private String password;
}
