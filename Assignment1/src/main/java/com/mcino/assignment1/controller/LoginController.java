package com.mcino.assignment1.controller;

import com.mcino.assignment1.model.Credential;
import com.mcino.assignment1.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginService service;

    @GetMapping(value="/")
    public String showLoginPage(){
        return "login";
    }

    @PostMapping(value="/")
    public String showHomePage(ModelMap model, HttpSession session, @RequestParam String username,
                               @RequestParam String password){
        Credential c = new Credential();
        c.setUsername(username);
        c.setPassword(password);

        boolean isValid = service.validateUser(c.getUsername(), c.getPassword());

        if(!isValid){
            model.put("error", "Invalid Credentials");
            return "login";
        }
        session.setAttribute("username", username);

        //student and staff won't have the same ids so set session based on that
        long studentId = service.isStudent(c);
        long coordinatorId = service.isStaff(c);

        if(studentId != -1) {
            session.setAttribute("role","student");
            session.setAttribute("id", studentId);
        }else if (coordinatorId != -1){
            session.setAttribute("role", "staff");
            session.setAttribute("id", coordinatorId);
        }
        return "redirect:home";
    }
}
