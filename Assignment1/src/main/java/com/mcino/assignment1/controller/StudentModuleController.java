package com.mcino.assignment1.controller;

import com.mcino.assignment1.model.StudentModule;
import com.mcino.assignment1.repository.StudentModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class StudentModuleController {

    @Autowired
    StudentModuleRepository studentModuleRepository;

    @GetMapping("/student/{id}")
    public List<StudentModule> getStudentGrades(@PathVariable(value = "id") Long studentId) {
        //todo get this to return StudentModule objects with a given student id
        return null;
    }

    @GetMapping("/module/{id}")
    public List<StudentModule> getModuleGrades() {
        //todo get this to return StudentModule objects with a given module idw
        return null;
    }
}
