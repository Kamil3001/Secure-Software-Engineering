package com.mcino.assignment1.controller;

import com.mcino.assignment1.Utils.FormValidationInformation;
import com.mcino.assignment1.model.Credential;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.repository.StudentRepository;
import com.mcino.assignment1.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("status")
public class RegisterController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RegistrationService service;

    @GetMapping(value="register")
    public String showRegistrationPage(){
        return "register";
    }

    @PostMapping(value="register")
    public String showLoginPage(ModelMap model, @RequestParam String username, @RequestParam String password,
                                @RequestParam String name,
                                @RequestParam String surname,
                                @RequestParam String nationality,
                                @RequestParam String gender,
                                @RequestParam String studentid,
                                @RequestParam String address,
                                @RequestParam String phonenumber,
                                @RequestParam String email){

        FormValidationInformation fvi = service.check(username, password, name, surname, nationality, gender, studentid, address, phonenumber, email);
        if(!fvi.isValid()){
            model.addAttribute("error", fvi.getMessage());
            return "register";
        } else{
            model.addAttribute("success", fvi.getMessage());
        }

        if(gender.equals("M")){
            gender = "Male";
        }else if(gender.equals("F")){
            gender = "Female";
        }

        Credential newCredentials = new Credential();
        newCredentials.setUsername(username);
        newCredentials.setPassword(password);

        Student newStudent = new Student();
        newStudent.setId(Long.parseLong(studentid));
        newStudent.setCredentials(newCredentials);
        newStudent.setName(name);
        newStudent.setSurname(surname);
        newStudent.setNationality(nationality);
        newStudent.setGender(gender);
        newStudent.setEmail(email);
        newStudent.setFeePaid(false);
        newStudent.setAddress(address);
        newStudent.setPhoneNum(phonenumber);

        studentRepository.save(newStudent);


        return "redirect:/";
    }
}
