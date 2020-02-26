package com.mcino.assignment1.service;

import com.mcino.assignment1.model.Credential;
import com.mcino.assignment1.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    CredentialsRepository credentialsRepository;

    public boolean validateUser(String username, String password){
        Credential findCredentials = credentialsRepository.findByUsernameAndPassword(username, password);
        return findCredentials != null;
    }
}
