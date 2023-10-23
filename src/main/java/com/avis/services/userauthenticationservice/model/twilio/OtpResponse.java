package com.avis.services.userauthenticationservice.model.twilio;

import com.twilio.rest.api.v2010.account.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OtpResponse {

    private OtpStatus status;
    private String description;
    private Message twilioMessage;

}
