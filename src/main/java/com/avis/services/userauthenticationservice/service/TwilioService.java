package com.avis.services.userauthenticationservice.service;

import com.avis.services.userauthenticationservice.model.twilio.OtpRequest;
import com.avis.services.userauthenticationservice.model.twilio.OtpResponse;
import com.avis.services.userauthenticationservice.model.twilio.OtpVerificationRequest;
import com.avis.services.userauthenticationservice.model.twilio.OtpVerificationResponse;

public interface TwilioService {

    OtpResponse sendSmsOtp(OtpRequest request);

    OtpVerificationResponse verifySmsOtp(OtpVerificationRequest request);
}
