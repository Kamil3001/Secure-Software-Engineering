package com.mcino.assignment1.controller;

import com.mcino.assignment1.exception.ModuleNotFoundException;
import com.mcino.assignment1.exception.StudentNotFoundException;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.service.ModuleService;
import com.mcino.assignment1.service.MyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    @Autowired
    MyProfileService myProfileService;

    @Autowired
    ModuleService moduleService;

    @RequestMapping(value="/home")
    public String showHomePage(ModelMap model){

        return "home";
    }

    @RequestMapping(value="/view_modules")
    public String showModulesPage(ModelMap model){
        model.addAttribute("all_modules", moduleService.retrieveAllModules());
        return "view_modules";
    }

    @RequestMapping(value="/my_profile")
    public String showMyProfilePage(HttpSession session, ModelMap model) throws StudentNotFoundException {
        long studentId = (long) session.getAttribute("id");
        model.addAttribute("student", myProfileService.retrieveStudent(studentId));
        model.addAttribute("curr_modules", myProfileService.retrieveStudentsModules(studentId));
        model.addAttribute("moduleGradeMap", myProfileService.retrieveTerminatedModules(studentId));
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

    @RequestMapping(value="/module/{id}")
    public String showModulePage(ModelMap model, @PathVariable(value = "id") long moduleId) throws ModuleNotFoundException {
        model.addAttribute("module", moduleService.retrieveModuleById(moduleId));
        return "module";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session, SessionStatus status){
        session.removeAttribute("username");
        session.removeAttribute("role");
        status.setComplete();
        return "redirect:/";
    }

}
