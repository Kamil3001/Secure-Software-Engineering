package com.mcino.assignment1.controller;

import com.mcino.assignment1.exception.CoordinatorNotFoundException;
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
    public String showMyProfilePage(HttpSession session, ModelMap model) throws StudentNotFoundException, CoordinatorNotFoundException {
        long userId = (long) session.getAttribute("id");
        if (session.getAttribute("role").equals("student")) {
            model.addAttribute("student", myProfileService.retrieveStudent(userId));
            model.addAttribute("curr_modules", myProfileService.retrieveStudentsModules(userId));
            model.addAttribute("moduleGradeMap", myProfileService.retrieveTerminatedModules(userId));
        }
        else if (session.getAttribute("role").equals("staff")) {
            model.addAttribute("coordinator", myProfileService.retrieveCoordinator(userId));
            model.addAttribute("taught_modules", myProfileService.retrieveCoordinatorsModules(userId));
        }
        return "my_profile";
    }

    @RequestMapping(value="/statistics")
    public String showStatisticsPage(ModelMap model){
        return "statistics";
    }

    @RequestMapping(value="/module/{id}")
    public String showModulePage(HttpSession session, ModelMap model, @PathVariable(value = "id") long moduleId) throws ModuleNotFoundException, CoordinatorNotFoundException {
        model.addAttribute("module", moduleService.retrieveModuleById(moduleId));
        model.addAttribute("numEnrolled", moduleService.retrieveEnrolledCount(moduleId));
        model.addAttribute("coordinator", moduleService.retrieveCoordinator(moduleId));
        if (session.getAttribute("role").equals("student"))
            model.addAttribute("isEnrolled", moduleService.isEnrolled((long) session.getAttribute("id"), moduleId));
        return "module";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session, SessionStatus status){
        session.removeAttribute("username");
        session.removeAttribute("role");
        session.removeAttribute("id");
        status.setComplete();
        return "redirect:/";
    }

}
