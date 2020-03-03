package com.mcino.assignment1.controller;

import com.mcino.assignment1.exception.StudentModuleNotFoundException;
import com.mcino.assignment1.model.StudentModule;
import com.mcino.assignment1.repository.StudentModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class StudentModuleController {

    @Autowired
    StudentModuleRepository studentModuleRepository;

//    @GetMapping("/grades/student/{id}")
//    public List<StudentModule> getStudentGrades(@PathVariable(value = "id") Long studentId) {
//        return studentModuleRepository.findByStudentId(studentId);
//    }

//    @GetMapping("/grades/module/{id}")
//    public List<StudentModule> getModuleGrades(@PathVariable(value = "id") Long moduleId) {
//        return studentModuleRepository.findByModuleId(moduleId);
//    }

    @RequestMapping("/students/{student_id}/drop/{module_id}")
    public String unEnrollFromModule(@PathVariable(value = "module_id") Long moduleId,
                                                @PathVariable(value = "student_id") Long studentId) {
        StudentModule studentModule = studentModuleRepository.findByStudentIdAndModuleId(studentId, moduleId);
        studentModuleRepository.delete(studentModule);
        return "redirect:/my_profile";
    }

    @PutMapping("/grades/{module_id}/update/{student_id}")
    public StudentModule updateGrade(@PathVariable(value = "module_id") Long moduleId,
                            @PathVariable(value = "student_id") Long studentId,
                            @Valid @RequestBody String grade) throws StudentModuleNotFoundException {
        StudentModule studentModule = studentModuleRepository.findByStudentIdAndModuleId(studentId, moduleId);
        if (studentModule == null)
            throw new StudentModuleNotFoundException(studentId, moduleId);

        studentModule.setGrade(grade);
        return studentModuleRepository.save(studentModule);
    }
}
