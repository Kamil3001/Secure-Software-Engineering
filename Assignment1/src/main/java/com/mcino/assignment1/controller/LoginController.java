package com.mcino.assignment1.controller;

import com.mcino.assignment1.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("username")
public class LoginController {

    @Autowired
    LoginService service;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "login";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String showHomePage(ModelMap model, @RequestParam String username,
                                  @RequestParam String password){
        boolean isValid = service.validateUser(username, password);

        if(!isValid){
            model.put("error", "Invalid credentials");
            return "login";
        }

        model.put("username", username);

        return "home";
    }
}
