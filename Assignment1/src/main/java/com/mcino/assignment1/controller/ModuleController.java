package com.mcino.assignment1.controller;

import com.mcino.assignment1.exception.ModuleNotFoundException;
import com.mcino.assignment1.model.Module;
import com.mcino.assignment1.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;

    @PutMapping("/{id}/update")
    public Module updateModuleDetails(@PathVariable(value = "id") Long moduleId,
                               @Valid @RequestBody Module moduleDetails) throws ModuleNotFoundException {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));

        module.setCapacity(moduleDetails.getCapacity());
        module.setCoordinatorId(moduleDetails.getCoordinatorId());
        module.setName(moduleDetails.getName());
        module.setTerminated(moduleDetails.isTerminated());
        module.setTopics(moduleDetails.getTopics());

        return moduleRepository.save(module);
    }

    @GetMapping
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Module getModuleById(@PathVariable(value = "id") Long moduleId) throws ModuleNotFoundException {
        return moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));
    }
}
