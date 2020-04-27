package com.mcino.assignment1.controller;

import com.mcino.assignment1.exception.StudentNotFoundException;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

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

    @RequestMapping("/{id}/unregister")
    public String deleteStudent(@PathVariable(value = "id") Long studentId, HttpSession session) throws StudentNotFoundException {

        if((session.getAttribute("role") == null || !session.getAttribute("role").equals("student")) || ((long) session.getAttribute("id")) != studentId){
            log.error("Attempt to delete student record for student id: '{}' with wrong/no role or as incorrect student w/ id: {}", studentId, session.getAttribute("id"));
            return "redirect:/error";
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        studentRepository.delete(student);
        log.info("Successfully deleted student record for student id: '{}'", studentId);
        return "redirect:/";
    }

    @RequestMapping("{id}/payFees")
    public String updateFees(@PathVariable(value = "id") Long studentId, HttpSession session) throws StudentNotFoundException {
        if((session.getAttribute("role") == null || !session.getAttribute("role").equals("student")) || ((long) session.getAttribute("id")) != studentId){
            log.error("Attempt to pay fees for student id: '{}' with wrong/no role or as incorrect student w/ id: {}", studentId, session.getAttribute("id"));
            return "redirect:/error";
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        student.setFeePaid(true);

        studentRepository.save(student);
        log.info("Successfully payed fees for student w/ id '{}'", studentId);
        return "redirect:/my_profile";
    }
}
