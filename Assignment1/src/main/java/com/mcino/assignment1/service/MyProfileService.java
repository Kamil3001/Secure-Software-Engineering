package com.mcino.assignment1.service;

import com.mcino.assignment1.exception.CoordinatorNotFoundException;
import com.mcino.assignment1.exception.StudentNotFoundException;
import com.mcino.assignment1.model.Coordinator;
import com.mcino.assignment1.model.Module;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.model.StudentModule;
import com.mcino.assignment1.repository.CoordinatorRepository;
import com.mcino.assignment1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MyProfileService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CoordinatorRepository coordinatorRepository;

    public Student retrieveStudent(long id) throws StudentNotFoundException {
        return studentRepository.findById(id)
                .orElseThrow( () ->new StudentNotFoundException(id));
    }

    public List<Module> retrieveStudentsModules(long id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        List<Module> modules = new ArrayList<>();
        for(StudentModule sm : student.getStudentModules()) {
            if(!sm.getModule().isTerminated())
                modules.add(sm.getModule());
        }

        return modules;
    }

    public HashMap<Long, String[]> retrieveTerminatedModules(long id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        HashMap<Long, String[]> moduleGrades = new HashMap<>();
        for(StudentModule sm : student.getStudentModules()) {
            if(sm.getModule().isTerminated()) {
                moduleGrades.put(sm.getModuleId(), new String[]{sm.getModule().getName(), sm.getGrade()});
            }
        }

        return moduleGrades;
    }

    public Coordinator retrieveCoordinator(long coordId) throws CoordinatorNotFoundException {
        return coordinatorRepository.findById(coordId)
                .orElseThrow(() -> new CoordinatorNotFoundException(coordId));
    }

    public List<Module> retrieveCoordinatorsModules(long coordId) throws CoordinatorNotFoundException {
        Coordinator coordinator = coordinatorRepository.findById(coordId)
                .orElseThrow(() -> new CoordinatorNotFoundException(coordId));
        return new ArrayList<>(coordinator.getModules());
    }
}
