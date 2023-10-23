package com.avis.services.userauthenticationservice.repository;

import com.avis.services.userauthenticationservice.entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwilioOtpRepository extends JpaRepository<OtpEntity, String> {
}
