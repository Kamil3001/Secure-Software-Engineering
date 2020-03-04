package com.mcino.assignment1.controller;

import com.mcino.assignment1.exception.StudentNotFoundException;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.model.StudentModule;
import com.mcino.assignment1.repository.StudentRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    public String deleteStudent(@PathVariable(value = "id") Long studentId) throws StudentNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        studentRepository.delete(student);
        return "redirect:/";
    }



//    @GetMapping("/{id}")
//    public Student getStudentById(@PathVariable(value = "id") Long studentId) throws StudentNotFoundException {
//        return studentRepository.findById(studentId)
//                .orElseThrow(() -> new StudentNotFoundException(studentId));
//    }

    @RequestMapping("{id}/payFees")
    public String updateFees(@PathVariable(value = "id") Long studentId) throws StudentNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        student.setFeePaid(true);

        studentRepository.save(student);
        return "redirect:/my_profile";
    }

//    @GetMapping("{id}/moduleList")
//    public Set<StudentModule> getStudentsModules(@PathVariable(value = "id") Long studentId) throws StudentNotFoundException {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new StudentNotFoundException(studentId));
//
//        return student.getStudentModules();
//    }
}
