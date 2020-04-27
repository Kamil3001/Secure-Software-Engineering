package com.mcino.assignment1.service;

import com.mcino.assignment1.model.Coordinator;
import com.mcino.assignment1.model.Credential;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.repository.CoordinatorRepository;
import com.mcino.assignment1.repository.CredentialsRepository;
import com.mcino.assignment1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CoordinatorRepository coordinatorRepository;

    public boolean validateUser(String username, String password){
        Credential findCredentials = credentialsRepository.findByUsername(username);

        if (findCredentials != null) {
            return BCrypt.checkpw(password, findCredentials.getPassword());
        }
        return false;
    }

    // Check if given credentials belong to a student
    public long isStudent(Credential c){
        Student student = studentRepository.findByCredentials(c);
        if(student == null)
            return -1L;
        return student.getId();
    }

    // Check if given credentials belong to a coordinator
    public long isStaff(Credential c) {
        Coordinator coordinator = coordinatorRepository.findByCredentials(c);
        if(coordinator == null)
            return -1L;
        return coordinator.getId();
    }
}
