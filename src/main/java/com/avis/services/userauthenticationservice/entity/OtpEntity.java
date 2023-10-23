package com.avis.services.userauthenticationservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otp_table")
@Setter
@Getter
public class OtpEntity {

    @Id
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "otp")
    private String otp;
}
