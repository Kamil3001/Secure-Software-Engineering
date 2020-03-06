package com.mcino.assignment1.service;

import com.mcino.assignment1.Utils.GradeQueryHelper;
import com.mcino.assignment1.Utils.NationalityQueryHelper;
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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Service
public class ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    StudentModuleRepository studentModuleRepository;

    @Autowired
    CoordinatorRepository coordinatorRepository;

    @PersistenceContext
    EntityManager em;

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

    public JSONObject retrieveGradesFromPreviousEdition(long moduleId){
        Optional<Module> module = moduleRepository.findById(moduleId);
        JSONObject json = new JSONObject();
        if(module.isPresent()){
            String moduleCode = module.get().getModuleCode();
            List<Module> modules = moduleRepository.findAllByModuleCode(moduleCode);
            if(!modules.isEmpty()){
                HashMap<String, Integer> gradeCounts = new HashMap<>();
                ArrayList<GradeQueryHelper> gradeResults = new ArrayList<>();
                for(Module m: modules) {
                    Query queryGrades = em.createNativeQuery("SELECT grade, COUNT(*) as total FROM student_module WHERE module_id='" + m.getId() + "' AND grade<>'' GROUP BY grade;", GradeQueryHelper.class);
                    ArrayList<GradeQueryHelper> tmpBuffer = (ArrayList<GradeQueryHelper>) queryGrades.getResultList();
                    if(!tmpBuffer.isEmpty()) {
                        gradeResults.addAll(queryGrades.getResultList());
                    }
                }
                if(!gradeResults.isEmpty()) {
                    // grouping all the grades
                    for (GradeQueryHelper g : gradeResults) {
                        String grade = g.getGrade();
                        int total = g.getTotal();
                        if(gradeCounts.containsKey(grade)){
                            gradeCounts.put(grade, gradeCounts.get(grade) + total);
                        }else{
                            gradeCounts.put(grade, total);
                        }
                    }
                    for(Map.Entry e: gradeCounts.entrySet()){
                        json.put(String.valueOf(e.getKey()), e.getValue());
                    }
                }else{
                    // no previous grades for the module
                    return null;
                }
            }else{
                // error
                return null;
            }
        }else{
            // error
            return null;
        }

        return json;
    }
}
