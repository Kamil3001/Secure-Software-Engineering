package com.mcino.assignment1.controller;

import com.mcino.assignment1.exception.StudentModuleNotFoundException;
import com.mcino.assignment1.model.StudentModule;
import com.mcino.assignment1.repository.StudentModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentModuleController {

    @Autowired
    StudentModuleRepository studentModuleRepository;

    @Autowired
    EntityManager em;

    @GetMapping("/grades/student/{id}")
    public List<StudentModule> getStudentGrades(@PathVariable(value = "id") Long studentId) {
        return studentModuleRepository.findByStudentId(studentId);
    }

    @GetMapping("/grades/module/{id}")
    public List<StudentModule> getModuleGrades(@PathVariable(value = "id") Long moduleId) {
        return studentModuleRepository.findByModuleId(moduleId);
    }

    @Transactional
    @PostMapping("modules/{id}/drop")
    public void unEnrollFromModule(@PathVariable(value = "id") Long moduleId,
                                   @Valid @RequestBody Long studentId) {
//        Student student = em.getReference(Student.class, studentId);
//        Module module = em.getReference(Module.class, moduleId);
//        module.removeStudent(student);
//        em.persist(student);
//        em.persist(module);
//        StudentModule studentModule = new StudentModule();
//        studentModule.setStudent(student);
//        studentModule.setModule(module);
//        studentModuleRepository.delete(studentModule);
        // todo try and figure out a way to remove entry from the join table
    }

    @PutMapping("grades/{module_id}/update/{student_id}")
    public StudentModule updateGrade(@PathVariable(value = "module_id") Long moduleId,
                            @PathVariable(value = "student_id") Long studentId,
                            @Valid @RequestBody String grade) throws StudentModuleNotFoundException {
        StudentModule studentModule = studentModuleRepository.findByStudentIdAndModuleId(studentId, moduleId);
        if (studentModule == null)
            throw new StudentModuleNotFoundException(studentId, moduleId);

        System.out.println(studentModule);
        studentModule.setGrade(grade);
        System.out.println(studentModule);
        return studentModuleRepository.save(studentModule);
    }
}
