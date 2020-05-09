package com.mcino.assignment1.service;

import com.mcino.assignment1.model.Coordinator;
import com.mcino.assignment1.model.Credential;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.repository.CoordinatorRepository;
import com.mcino.assignment1.repository.CredentialsRepository;
import com.mcino.assignment1.repository.StudentRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginService {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CoordinatorRepository coordinatorRepository;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;

    public boolean validateUser(String username, String password){
        String ip = getIP();
        if(loginAttemptService.isBlacklisted(ip)){
            System.out.println("Blacklisted IP : " + ip);
            return false;
        }

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

    private String getIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
