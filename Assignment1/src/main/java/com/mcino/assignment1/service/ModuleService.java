package com.mcino.assignment1.service;

import com.mcino.assignment1.Utils.StudentGrade;
import com.mcino.assignment1.Utils.StudentGradesForm;
import com.mcino.assignment1.exception.CoordinatorNotFoundException;
import com.mcino.assignment1.exception.ModuleNotFoundException;
import com.mcino.assignment1.model.Coordinator;
import com.mcino.assignment1.model.Module;
import com.mcino.assignment1.model.StudentModule;
import com.mcino.assignment1.repository.CoordinatorRepository;
import com.mcino.assignment1.repository.ModuleRepository;
import com.mcino.assignment1.repository.StudentModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    StudentModuleRepository studentModuleRepository;

    @Autowired
    CoordinatorRepository coordinatorRepository;

    public List<Module> retrieveAllModules() {
        return moduleRepository.findAll();
    }

    public Module retrieveModuleById(long moduleId) throws ModuleNotFoundException {
        return moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));
    }

    public int retrieveEnrolledCount(long moduleId) {
        return studentModuleRepository.findByModuleId(moduleId).size();
    }

    public Coordinator retrieveCoordinator(long moduleId) throws ModuleNotFoundException, CoordinatorNotFoundException {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));
        return coordinatorRepository.findById(module.getCoordinatorId())
                .orElseThrow(() -> new CoordinatorNotFoundException(module.getCoordinatorId()));
    }

    public boolean isEnrolled(long studentId, long moduleId) {
        List<StudentModule> studentModules = studentModuleRepository.findByStudentId(studentId);

        for(StudentModule sm : studentModules) {
            if (sm.getModuleId() == moduleId)
                return true;
        }
        return false;
    }

    public StudentGradesForm retrieveStudentGradesForm(long moduleId) {
        List<StudentModule> studentModules = studentModuleRepository.findByModuleId(moduleId);

        List<StudentGrade> studentGrades = new ArrayList<>();
        StudentGrade sg;
        for(StudentModule sm : studentModules) {
            sg = new StudentGrade();
            sg.setStudentId(sm.getStudentId());
            sg.setModuleId(sm.getModuleId());
            sg.setGrade(sm.getGrade());
            sg.setStudentFullName(sm.getStudent().getName() + " " + sm.getStudent().getSurname());
            studentGrades.add(sg);
        }
        StudentGradesForm sgf = new StudentGradesForm();
        sgf.setStudentGrades(studentGrades);
        return sgf;
    }
}
