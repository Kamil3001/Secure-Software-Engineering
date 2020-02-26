package com.mcino.assignment1.repository;

import com.mcino.assignment1.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CredentialsRepository extends JpaRepository<Credential, Long>, JpaSpecificationExecutor<Credential> {
    Credential findByUsernameAndPassword(String username, String password);
    Credential findByUsername(String username);
}
