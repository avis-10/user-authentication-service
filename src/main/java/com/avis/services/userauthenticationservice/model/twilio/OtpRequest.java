package com.avis.services.userauthenticationservice.model.twilio;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OtpRequest {

    private String username;
    private String phone;
}
