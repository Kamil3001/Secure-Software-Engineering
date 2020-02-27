package com.mcino.assignment1.controller;

import com.mcino.assignment1.exception.StudentModuleNotFoundException;
import com.mcino.assignment1.model.StudentModule;
import com.mcino.assignment1.repository.StudentModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentModuleController {

    @Autowired
    StudentModuleRepository studentModuleRepository;

    @GetMapping("/grades/student/{id}")
    public List<StudentModule> getStudentGrades(@PathVariable(value = "id") Long studentId) {
        return studentModuleRepository.findByStudentId(studentId);
    }

    @GetMapping("/grades/module/{id}")
    public List<StudentModule> getModuleGrades(@PathVariable(value = "id") Long moduleId) {
        return studentModuleRepository.findByModuleId(moduleId);
    }

    @Transactional
    @DeleteMapping("students/{student_id}/drop/{module_id}")
    public ResponseEntity<?> unEnrollFromModule(@PathVariable(value = "module_id") Long moduleId,
                                                @PathVariable(value = "student_id") Long studentId) {
        StudentModule studentModule = studentModuleRepository.findByStudentIdAndModuleId(studentId, moduleId);
        studentModuleRepository.delete(studentModule);
        return ResponseEntity.ok().build();
    }

    @PutMapping("grades/{module_id}/update/{student_id}")
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
