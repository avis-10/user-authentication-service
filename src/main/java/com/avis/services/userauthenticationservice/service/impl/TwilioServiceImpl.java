package com.avis.services.userauthenticationservice.service.impl;

import com.avis.services.userauthenticationservice.entity.OtpEntity;
import com.avis.services.userauthenticationservice.integrations.config.TwilioConfig;
import com.avis.services.userauthenticationservice.model.twilio.*;
import com.avis.services.userauthenticationservice.repository.TwilioOtpRepository;
import com.avis.services.userauthenticationservice.service.TwilioService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Random;

@Service
public class TwilioServiceImpl implements TwilioService {

    @Autowired
    private TwilioConfig twilioConfig;

    @Autowired
    private TwilioOtpRepository twilioOtpRepository;

    @Override
    public OtpResponse sendSmsOtp(OtpRequest request) {
        PhoneNumber from = new PhoneNumber(twilioConfig.getPHONE_NUMBER());
        PhoneNumber to = new PhoneNumber(request.getPhone());
        String otp = generateOtp();
        String otpMessage = "Dear "+request.getUsername()+", Your OTP is  "+otp+
                " and is sent for verification. Please don't share this OTP with anyone. Thank You.";
        try {
            Twilio.init(twilioConfig.getACCOUNT_SID(), twilioConfig.getAUTH_TOKEN());
            Message message = Message.creator( to, from, otpMessage).create();
            persistOtp(request.getPhone(), otp);
            return OtpResponse.builder().status(OtpStatus.DELIVERED).description(otpMessage)
                    .twilioMessage(message).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return OtpResponse.builder().status(OtpStatus.FAILED).description(ex.getMessage())
                    .build();
        }
    }

    @Override
    public OtpVerificationResponse verifySmsOtp(OtpVerificationRequest request) {
        OtpEntity dbEntity = twilioOtpRepository.findById(request.getPhone()).orElse(null);
        if (Objects.nonNull(dbEntity.getPhoneNumber())) {
            String otpFromDb = dbEntity.getOtp();
            if (request.getOtp().equals(otpFromDb)) {
                return OtpVerificationResponse.builder().description(request.getUsername() + ", You are verified.").build();
            } else {
                return OtpVerificationResponse.builder().description(request.getUsername() + ", Wrong OTP !!").build();
            }
        } else {
            return OtpVerificationResponse.builder().description(request.getUsername() + ", Wrong mobile number").build();
        }
    }

    private void persistOtp(String phone, String otp) {
        OtpEntity entity = new OtpEntity();
        entity.setPhoneNumber(phone);
        entity.setOtp(otp);
        twilioOtpRepository.save(entity);
    }

    private String generateOtp() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }
}
