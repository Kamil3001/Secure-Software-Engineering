package com.mcino.assignment1.service;

import com.mcino.assignment1.exception.StudentNotFoundException;
import com.mcino.assignment1.model.Module;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.model.StudentModule;
import com.mcino.assignment1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyProfileService {

    @Autowired
    StudentRepository studentRepository;

    public Student retrieveStudent(long id) throws StudentNotFoundException {
        return studentRepository.findById(id)
                .orElseThrow( () ->new StudentNotFoundException(id));
    }

    public List<Module> retrieveStudentsModules(long id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        List<Module> modules = new ArrayList<>();
        for(StudentModule sm : student.getStudentModules()) {
            modules.add(sm.getModule());
        }

        return modules;
    }
}
