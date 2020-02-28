package com.mcino.assignment1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class WebController {

    @RequestMapping(value="/home")
    public String showHomePage(ModelMap model){
        return "home";
    }

    @RequestMapping(value="/view_modules")
    public String showModulesPage(ModelMap model){
        return "view_modules";
    }

    @RequestMapping(value="/my_profile")
    public String showMyProfilePage(ModelMap model){
        return "my_profile";
    }

    @RequestMapping(value="/staff")
    public String showStaffPage(ModelMap model){
        return "staff";
    }

    @RequestMapping(value="/statistics")
    public String showStatisticsPage(ModelMap model){
        return "statistics";
    }

}
