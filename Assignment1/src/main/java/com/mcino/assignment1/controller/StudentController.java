package com.mcino.assignment1.controller;

import com.mcino.assignment1.exception.StudentNotFoundException;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/register")
    public Student registerStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}/unregister")
    public String deleteStudent(@PathVariable(value = "id") Long studentId, HttpSession session) throws StudentNotFoundException {
        if(!session.getAttribute("role").equals("student") || ((long) session.getAttribute("id")) != studentId){
            return "redirect:/error";
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        studentRepository.delete(student);
        return "redirect:/";
    }

    @RequestMapping("{id}/payFees")
    public String updateFees(@PathVariable(value = "id") Long studentId, HttpSession session) throws StudentNotFoundException {
        if(!session.getAttribute("role").equals("student") || ((long) session.getAttribute("id")) != studentId){
            return "redirect:/error";
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        student.setFeePaid(true);

        studentRepository.save(student);
        return "redirect:/my_profile";
    }
}
