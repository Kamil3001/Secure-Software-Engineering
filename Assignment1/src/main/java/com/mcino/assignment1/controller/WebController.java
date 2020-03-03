package com.mcino.assignment1.controller;

import com.mcino.assignment1.exception.StudentNotFoundException;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.service.MyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    @Autowired
    MyProfileService myProfileService;

    @RequestMapping(value="/home")
    public String showHomePage(ModelMap model){

        return "home";
    }

    @RequestMapping(value="/view_modules")
    public String showModulesPage(ModelMap model){
        return "view_modules";
    }

    @RequestMapping(value="/my_profile")
    public String showMyProfilePage(HttpSession session, ModelMap model) throws StudentNotFoundException {
        long studentId = (long) session.getAttribute("id");
        model.addAttribute("student", myProfileService.retrieveStudent(studentId));
        model.addAttribute("modules", myProfileService.retrieveStudentsModules(studentId));
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

    @RequestMapping(value="/logout")
    public String logout(HttpSession session, SessionStatus status){
        session.removeAttribute("username");
        session.removeAttribute("role");
        status.setComplete();
        return "redirect:/";
    }

}
