package com.mcino.assignment1.controller;

import com.mcino.assignment1.Utils.StudentGrade;
import com.mcino.assignment1.Utils.StudentGradesForm;
import com.mcino.assignment1.exception.ModuleNotFoundException;
import com.mcino.assignment1.model.Module;
import com.mcino.assignment1.model.StudentModule;
import com.mcino.assignment1.repository.StudentModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentModuleController {

    @Autowired
    StudentModuleRepository studentModuleRepository;

    @RequestMapping("/students/{student_id}/drop/{module_id}")
    public String unEnrollFromModule(@PathVariable(value = "module_id") Long moduleId,
                                     @PathVariable(value = "student_id") Long studentId, HttpSession session) {
        if(!session.getAttribute("role").equals("student") || ((long) session.getAttribute("id")) != studentId){
            return "redirect:/error";
        }
        StudentModule studentModule = studentModuleRepository.findByStudentIdAndModuleId(studentId, moduleId);
        studentModuleRepository.delete(studentModule);
        return "redirect:/my_profile";
    }

    @PostMapping("/grades/{module_id}/update/")
    public String updateGrade(@PathVariable(value = "module_id") Long moduleId,
                              @Valid @ModelAttribute("studentModules")StudentGradesForm studentGradesForm, HttpSession session) {

        if(!session.getAttribute("role").equals("staff")){
            return "redirect:/error";
        }

        Module module = studentModuleRepository.findByModuleId(moduleId).get(0).getModule();

        if( ((long) session.getAttribute("id")) != module.getCoordinatorId()){
            return "redirect:/error";
        }

        List<StudentModule> studentModules = new ArrayList<>();
        StudentModule sm;
        for(StudentGrade sg : studentGradesForm.getStudentGrades()) {
            sm = studentModuleRepository.findByStudentIdAndModuleId(sg.getStudentId(), moduleId);
            sm.setGrade(sg.getGrade());
            studentModules.add(sm);
        }
        studentModuleRepository.saveAll(studentModules);

        return "redirect:/module/" + moduleId;
    }
}
