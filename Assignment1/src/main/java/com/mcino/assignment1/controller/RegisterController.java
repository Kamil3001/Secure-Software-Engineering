package com.mcino.assignment1.controller;

import com.mcino.assignment1.Utils.FormValidationInformation;
import com.mcino.assignment1.model.Credential;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.repository.StudentRepository;
import com.mcino.assignment1.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("status")
public class RegisterController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RegistrationService service;

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(ModelMap model){
        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String showLoginPage(ModelMap model, @RequestParam String username, @RequestParam String password,
                                @RequestParam String name,
                                @RequestParam String surname,
                                @RequestParam String student_id,
                                @RequestParam String address,
                                @RequestParam String phone_number,
                                @RequestParam String email){

        FormValidationInformation fvi;
        if(!(fvi = service.check(username, password, name, surname, student_id, address, phone_number, email)).isValid()){
            model.put("error", fvi.getMessage());
            return "register";
        }/*else{
            model.put("success", fvi.getMessage()); // fix using redirect attributes
        }*/

        Credential newCredentials = new Credential();
        newCredentials.setUsername(username);
        newCredentials.setPassword(password);

        Student newStudent = new Student();
        newStudent.setId(Long.parseLong(student_id));
        newStudent.setCredentials(newCredentials);
        newStudent.setName(name);
        newStudent.setSurname(surname);
        newStudent.setEmail(email);
        newStudent.setFeePaid(false);
        newStudent.setAddress(address);
        newStudent.setPhoneNum(phone_number);

        studentRepository.save(newStudent);


        return "redirect:/";
    }
}
