package com.mcino.assignment1.controller;

import com.mcino.assignment1.Utils.FormValidationInformation;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.repository.StudentRepository;
import com.mcino.assignment1.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

@Controller
@SessionAttributes("status")
public class RegisterController {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RegistrationService service;

    @GetMapping(value="/register")
    public String showRegistrationPage(ModelMap model){
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping(value="/register")
    public String showLoginPage(ModelMap model,
                                @Valid @ModelAttribute("student") Student student){
        FormValidationInformation fvi = service.check(student);
        if(!fvi.isValid()){
            model.addAttribute("error", fvi.getMessage());
            log.error("Error with registration - Form Validation Unsuccessful");
            return "register";
        } else{
            model.addAttribute("success", fvi.getMessage());
        }

        studentRepository.save(student);

        log.info("Successfully registered a student w/ id '{}'", student.getId());
        return "redirect:/";
    }
}
