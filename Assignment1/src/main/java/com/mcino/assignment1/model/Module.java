package com.mcino.assignment1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "modules")
@Data
public class Module implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private long coordinatorId;

    @NotNull
    private String topics;

    @NotNull
    private boolean isTerminated;

    @NotNull
    private int capacity;

    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private Set<StudentModule> moduleStudents = new HashSet<>();

    public void addStudent(Student student) {
        StudentModule studentModule = new StudentModule();
        studentModule.setStudent(student);
        studentModule.setModule(this);
        moduleStudents.add(studentModule);
    }

//    public void removeStudent(Student student) {
//        StudentModule studentModule = new StudentModule();
//        studentModule.setStudent(student);
//        studentModule.setModule(this);
//        moduleStudents.remove(studentModule);
//        student.getStudentModules().remove(studentModule);
//
//    }
}
