package com.mcino.assignment1.controller;

import com.mcino.assignment1.exception.ModuleNotFoundException;
import com.mcino.assignment1.model.Module;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    private EntityManager em;

    @PostMapping("/{id}/update")
    public String updateModuleDetails(@PathVariable(value = "id") Long moduleId,
                                      @Valid @ModelAttribute("module") Module moduleDetails) throws ModuleNotFoundException {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));

        module.setCapacity(moduleDetails.getCapacity());
        module.setName(moduleDetails.getName());
        module.setTopics(moduleDetails.getTopics());

        moduleRepository.save(module);

        return "redirect:/module/"+moduleId;
    }

    @RequestMapping("/{id}/terminate")
    public String terminateModule(@PathVariable(value = "id") Long moduleId) throws ModuleNotFoundException {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));

        module.setTerminated(true);
        moduleRepository.save(module);
        return "redirect:/module/"+moduleId;
    }

    @Transactional
    @RequestMapping("/{module_id}/enroll/{student_id}")
    public String enrollToModule(@PathVariable(value = "module_id") Long moduleId, @PathVariable(value = "student_id") Long studentId) {
        Module module = em.getReference(Module.class, moduleId);
        Student student = em.getReference(Student.class, studentId);
        module.addStudent(student);
        em.persist(module);

        return "redirect:/module/"+moduleId;
    }
}
