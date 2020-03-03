package com.mcino.assignment1.service;

import com.mcino.assignment1.exception.ModuleNotFoundException;
import com.mcino.assignment1.model.Module;
import com.mcino.assignment1.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    public List<Module> retrieveAllModules() {
        return moduleRepository.findAll();
    }

    public Module retrieveModuleById(long moduleId) throws ModuleNotFoundException {
        return moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));
    }

}
