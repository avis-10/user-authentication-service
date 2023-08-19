package com.avis.services.userauthenticationservice.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepo extends JpaRepository<AuthUser, String> {
}
