package com.mcino.assignment1.controller;

import com.mcino.assignment1.Utils.GenderQueryHelper;
import com.mcino.assignment1.Utils.NationalityQueryHelper;
import com.mcino.assignment1.exception.CoordinatorNotFoundException;
import com.mcino.assignment1.exception.ModuleNotFoundException;
import com.mcino.assignment1.exception.StudentNotFoundException;
import com.mcino.assignment1.service.ModuleService;
import com.mcino.assignment1.service.MyProfileService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class WebController {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MyProfileService myProfileService;

    @Autowired
    ModuleService moduleService;

    @RequestMapping(value="/home")
    public String showHomePage(ModelMap model){
        return "home";
    }

    @RequestMapping(value="/view_modules")
    public String showModulesPage(ModelMap model){
        model.addAttribute("all_modules", moduleService.retrieveAllModules());
        return "view_modules";
    }

    @RequestMapping(value="/my_profile")
    public String showMyProfilePage(HttpSession session, ModelMap model) throws StudentNotFoundException, CoordinatorNotFoundException {
        long userId = (long) session.getAttribute("id");
        if (session.getAttribute("role").equals("student")) {
            model.addAttribute("student", myProfileService.retrieveStudent(userId));
            model.addAttribute("curr_modules", myProfileService.retrieveStudentsModules(userId));
            model.addAttribute("moduleGradeMap", myProfileService.retrieveTerminatedModules(userId));
        }
        else if (session.getAttribute("role").equals("staff")) {
            model.addAttribute("coordinator", myProfileService.retrieveCoordinator(userId));
            model.addAttribute("taught_modules", myProfileService.retrieveCoordinatorsModules(userId));
        }
        return "my_profile";
    }

    @RequestMapping(value="/statistics")
    public String showStatisticsPage(ModelMap model){
        JSONObject nationalityData = getNationalityStatistics();
        JSONObject genderData = getGenderStatistics();

        model.addAttribute("nationality", nationalityData);
        model.addAttribute("gender", genderData);

        return "statistics";
    }

    @RequestMapping(value="/module/{id}")
    public String showModulePage(HttpSession session, ModelMap model, @PathVariable(value = "id") long moduleId) throws ModuleNotFoundException, CoordinatorNotFoundException, StudentNotFoundException {
        model.addAttribute("module", moduleService.retrieveModuleById(moduleId));
        model.addAttribute("numEnrolled", moduleService.retrieveEnrolledCount(moduleId));
        model.addAttribute("coordinator", moduleService.retrieveCoordinator(moduleId));
        if (session.getAttribute("role").equals("student")) {
            model.addAttribute("isEnrolled", moduleService.isEnrolled((long) session.getAttribute("id"), moduleId));
            model.addAttribute("feesPaid", myProfileService.retrieveStudent((long)session.getAttribute("id")).isFeePaid());
        }
        else if (session.getAttribute("role").equals("staff")) {
            model.addAttribute("studentModules", moduleService.retrieveStudentModules(moduleId));
            model.addAttribute("gradeChoices", new String[] {"", "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E+", "E", "E-", "F+", "F", "F-", "NG"});
        }
        return "module";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session, SessionStatus status){
        session.removeAttribute("username");
        session.removeAttribute("role");
        session.removeAttribute("id");
        status.setComplete();
        return "redirect:/";
    }

    private JSONObject getNationalityStatistics(){
        Query queryStudent = em.createNativeQuery("SELECT nationality, COUNT(*) as total FROM students GROUP BY nationality", NationalityQueryHelper.class);
        Query queryStaff = em.createNativeQuery("SELECT nationality, COUNT(*) as total FROM coordinators GROUP BY nationality", NationalityQueryHelper.class);
        ArrayList<NationalityQueryHelper> bufferResult = new ArrayList<>();
        bufferResult.addAll(queryStudent.getResultList());
        bufferResult.addAll(queryStaff.getResultList());

        HashMap<String, Integer> queryResult = new HashMap<>();
        for(NationalityQueryHelper o: bufferResult){
            String nationality = o.getNationality();
            int total = o.getTotal();
            if(queryResult.containsKey(nationality)){
                queryResult.put(nationality, queryResult.get(nationality) + total);
            }else{
                queryResult.put(nationality, total);
            }
        }

        JSONObject json = new JSONObject();
        for(Map.Entry e: queryResult.entrySet()){
            json.put(String.valueOf(e.getKey()), e.getValue());
        }

        return json;    }

    private JSONObject getGenderStatistics(){
        Query queryStudent = em.createNativeQuery("SELECT gender, COUNT(*) as total FROM students GROUP BY gender", GenderQueryHelper.class);
        Query queryStaff = em.createNativeQuery("SELECT gender, COUNT(*) as total FROM coordinators GROUP BY gender", GenderQueryHelper.class);
        ArrayList<GenderQueryHelper> bufferResult = new ArrayList<>();
        bufferResult.addAll(queryStudent.getResultList());
        bufferResult.addAll(queryStaff.getResultList());

        HashMap<String, Integer> queryResult = new HashMap<>();
        for(GenderQueryHelper o: bufferResult){
            String gender = o.getGender();
            int total = o.getTotal();
            if(queryResult.containsKey(gender)){
                queryResult.put(gender, queryResult.get(gender) + total);
            }else{
                queryResult.put(gender, total);
            }
        }

        JSONObject json = new JSONObject();
        for(Map.Entry e: queryResult.entrySet()){
            json.put(String.valueOf(e.getKey()), e.getValue());
        }

        return json;

    }

}
