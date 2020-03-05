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

    public FormValidationInformation check(Student student){

        FormValidationInformation fvi = new FormValidationInformation();

        String nameRegex = "^[\\p{L}’\\-]+$";
        String usernameRegex = "^[\\p{Alnum}]{8,}$"; // alphanumeric with at least 8 characters
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!?])(?=\\S+$).{8,}$"; // at least one digit
        // at least one lower case
        // at least one upper case
        // at least one special character
        // no whitespace
        // at least 8 characters

        if(!student.getCredentials().getUsername().matches(usernameRegex)){
            fvi.setValid(false);
            if(student.getCredentials().getUsername().length() < 8) {
                fvi.setMessage("Username must be at least 8 characters long");
            }else{
                fvi.setMessage("Username must contain only alphanumeric characters");
            }
            return fvi;
        }
        else if(!student.getCredentials().getPassword().matches(passwordRegex)){
            fvi.setValid(false);
            fvi.setMessage("Password must have at least 1 of each - [0-9][a-z][A-Z][@#$%^!&+?=], and be " +
                    "at least 8 characters in length");
            return fvi;
        }
        else if(!student.getName().matches(nameRegex) || !student.getSurname().matches(nameRegex)){
            fvi.setValid(false);
            fvi.setMessage("Name or surname not valid");
            return fvi;
        }
        else if(student.getAddress().isEmpty() || student.getEmail().isEmpty() || student.getNationality().isEmpty()){
            fvi.setValid(false);
            fvi.setMessage("Some fields are empty");
            return fvi;
        }else if(student.getPhoneNum().length() < 10){
            fvi.setValid(false);
            fvi.setMessage("Invalid phone number");
        }else if(student.getGender().isEmpty() || student.getGender().charAt(0) != 'M' || student.getGender().charAt(0) != 'F'){
            fvi.setValid(false);
            fvi.setMessage("Ensure you fill the gender field correctly.");
        }

        //All information is valid, now check if some entries already exist
        Optional<Student> existingStudentId = studentRepository.findById(student.getId());
        Student existingEmail = studentRepository.findByEmail(student.getEmail());
        Credential existingUsername = credentialsRepository.findByUsername(student.getCredentials().getUsername());

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
