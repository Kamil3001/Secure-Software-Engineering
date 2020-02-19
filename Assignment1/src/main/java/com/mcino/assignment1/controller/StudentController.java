package com.mcino.assignment1.controller;

import com.mcino.assignment1.exception.StudentNotFoundException;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.model.StudentModule;
import com.mcino.assignment1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/register")
    public Student registerStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}/unregister")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Long studentId) throws StudentNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        studentRepository.delete(student);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable(value = "id") Long studentId) throws StudentNotFoundException {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }

    @PutMapping("{id}/payFees/")
    public Student updateFees(@PathVariable(value = "id") Long studentId) throws StudentNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        student.setFeePaid(true);

        return studentRepository.save(student);
    }

    @GetMapping("{id}/moduleList/")
    public Set<StudentModule> getStudentsModules(@PathVariable(value = "id") Long studentId) throws StudentNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        return student.getStudentModules();
    }
}
