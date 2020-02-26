package com.mcino.assignment1.service;

import com.mcino.assignment1.Utils.FormValidationInformation;
import com.mcino.assignment1.model.Credential;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.repository.CredentialsRepository;
import com.mcino.assignment1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    public FormValidationInformation check(String username,  String password, String name,
                           String surname,
                           String student_id,
                           String address,
                           String phone_number,
                           String email){

        FormValidationInformation fvi = new FormValidationInformation();
        
        String nameRegex = "^[\\p{L}’\\-]+$";
        String usernameRegex = "^[\\p{Alnum}]{8,}$"; // alphanumeric with at least 8 characters
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!?])(?=\\S+$).{8,}$"; // at least one digit
                                                                                                    // at least one lower case
                                                                                                    // at least one upper case
                                                                                                    // at least one special character
                                                                                                    // no whitespace
                                                                                                    // at least 8 characters

        if(!username.matches(usernameRegex)){
            fvi.setValid(false);
            if(username.length() < 8) {
                fvi.setMessage("Username must be at least 8 characters long");
            }else{
                fvi.setMessage("Username must contain only alphanumeric characters");
            }
            return fvi;
        }
        else if(!password.matches(passwordRegex)){
            fvi.setValid(false);
            fvi.setMessage("Password must have at least 1 of each - [0-9][a-z][A-Z][@#$%^!&+?=], and be " +
                    "at least 8 characters in length");
            return fvi;
        }
        else if(!name.matches(nameRegex) || !surname.matches(nameRegex)){
            fvi.setValid(false);
            fvi.setMessage("Name or surname not valid");
            return fvi;
        }
        else if(address.isEmpty() || email.isEmpty()){
            fvi.setValid(false);
            fvi.setMessage("Some fields are empty");
            return fvi;
        }else if(phone_number.length() < 10){
            fvi.setValid(false);
            fvi.setMessage("Invalid phone number");
        }

        try {
            Long.parseLong(student_id);
        }catch(Exception e){
            fvi.setValid(false);
            fvi.setMessage("Invalid student ID, make sure it consists of digits only");
            return fvi;
        }

        //All information is valid, now check if some entries already exist
        Optional<Student> existingStudentId = studentRepository.findById(Long.parseLong(student_id));
        Student existingEmail = studentRepository.findByEmail(email);
        Credential existingUsername = credentialsRepository.findByUsername(username);

        if(existingStudentId.isPresent()){
            fvi.setValid(false);
            fvi.setMessage("Entered student ID already exists");
            return fvi;
        }else if(existingEmail != null){
            fvi.setValid(false);
            fvi.setMessage("Entered email already exists");
            return fvi;
        }else if(existingUsername != null){
            fvi.setValid(false);
            fvi.setMessage("Entered username already exists");
            return fvi;
        }

        // All information is valid and registration can be completed
        fvi.setValid(true);
        fvi.setMessage("Registration successful");
        return fvi;

    }
}
