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
import javax.servlet.http.HttpSession;
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
                                      @Valid @ModelAttribute("module") Module moduleDetails, HttpSession session) throws ModuleNotFoundException {

        if(!session.getAttribute("role").equals("staff")){
            return "redirect:/error";
        }

        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));

        if( ((long) session.getAttribute("id")) != module.getCoordinatorId()){
            return "redirect:/error";
        }

        // Only capacity, name and topics can be modified
        module.setCapacity(moduleDetails.getCapacity());
        module.setName(moduleDetails.getName());
        module.setTopics(moduleDetails.getTopics());

        moduleRepository.save(module);

        return "redirect:/module/"+moduleId;
    }

    @RequestMapping("/{id}/terminate")
    public String terminateModule(@PathVariable(value = "id") Long moduleId, HttpSession session) throws ModuleNotFoundException {
        if(!session.getAttribute("role").equals("staff")){
            return "redirect:/error";
        }

        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));

        if( ((long) session.getAttribute("id")) != module.getCoordinatorId()){
            return "redirect:/error";
        }

        module.setTerminated(true);
        moduleRepository.save(module);
        return "redirect:/module/"+moduleId;
    }

    @Transactional // needed EntityManager to get this working
    @RequestMapping("/{module_id}/enroll/{student_id}")
    public String enrollToModule(@PathVariable(value = "module_id") Long moduleId, @PathVariable(value = "student_id") Long studentId, HttpSession session) {
        // to modify studentModule lists we need to modify the specific reference of Module hence EntityManager
        if(!session.getAttribute("role").equals("student") || ((long) session.getAttribute("id") != studentId)){
            return "redirect:/error";
        }
        Module module = em.getReference(Module.class, moduleId);
        Student student = em.getReference(Student.class, studentId);
        module.addStudent(student);
        em.persist(module); // persist -> save for repository

        return "redirect:/module/"+moduleId;
    }
}
