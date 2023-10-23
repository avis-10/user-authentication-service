package com.avis.services.userauthenticationservice.model.twilio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class OtpVerificationResponse {
    private String description;
}