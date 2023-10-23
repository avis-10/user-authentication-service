package com.avis.services.userauthenticationservice.controller;

import com.avis.services.userauthenticationservice.model.twilio.OtpRequest;
import com.avis.services.userauthenticationservice.model.twilio.OtpResponse;
import com.avis.services.userauthenticationservice.model.twilio.OtpVerificationRequest;
import com.avis.services.userauthenticationservice.model.twilio.OtpVerificationResponse;
import com.avis.services.userauthenticationservice.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/twilio")
public class TwilioController {

    @Autowired
    private TwilioService twilioService;

    @PostMapping("/otp/send-otp")
    public ResponseEntity<OtpResponse> sendOtp(@RequestBody OtpRequest request) {
        OtpResponse response = twilioService.sendSmsOtp(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/otp/verify-otp")
    public ResponseEntity<OtpVerificationResponse> verifyOtp(@RequestBody OtpVerificationRequest request) {
        OtpVerificationResponse response = twilioService.verifySmsOtp(request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
