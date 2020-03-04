package com.mcino.assignment1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "modules")
@Data
public class Module implements Serializable {

    private long coordinatorId;
    private boolean isTerminated;
    private int capacity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String moduleCode;

    @NotNull
    private String topics;

    @ToString.Exclude
//    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private Set<StudentModule> moduleStudents = new HashSet<>();

    public void addStudent(Student student) {
        StudentModule studentModule = new StudentModule();
        studentModule.setStudent(student);
        studentModule.setStudentId(student.getId());
        studentModule.setModule(this);
        studentModule.setModuleId(this.id);
        moduleStudents.add(studentModule);
    }
}
