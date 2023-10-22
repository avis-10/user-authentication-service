package com.avis.services.userauthenticationservice.security.repo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class AuthUser {

    @Id
    @Column(name = "user_name")
    private String username;
    @Column(name = "enc_password")
    private String encryptedPassword;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email_id")
    private String emailId;
}
