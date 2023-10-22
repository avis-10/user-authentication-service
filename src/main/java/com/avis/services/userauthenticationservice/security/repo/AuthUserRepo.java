package com.avis.services.userauthenticationservice.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthUserRepo extends JpaRepository<AuthUser, String> {
    Optional<AuthUser> findByUuid(String id);
}
