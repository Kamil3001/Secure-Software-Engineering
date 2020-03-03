package com.mcino.assignment1.service;

import com.mcino.assignment1.model.Credential;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.repository.CredentialsRepository;
import com.mcino.assignment1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    StudentRepository studentRepository;

    public boolean validateUser(String username, String password){
        Credential findCredentials = credentialsRepository.findByUsernameAndPassword(username, password);
        return findCredentials != null;
    }

    public long isStudent(Credential c){
        Student student = studentRepository.findByCredentials(c);
        if(student == null)
            return -1L;
        return student.getId();
    }
}
