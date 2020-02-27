package com.mcino.assignment1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@IdClass(StudentModuleId.class)
public class StudentModule implements Serializable {

    @Id
    @Column(name = "student_id")
    private long studentId;

    @Id
    @Column(name = "module_id")
    private long moduleId;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JoinColumn(name = "module_id", insertable = false, updatable = false)
    @JsonIgnore
    private Module module;

    @Column(length = 2)
    private String grade;
}
