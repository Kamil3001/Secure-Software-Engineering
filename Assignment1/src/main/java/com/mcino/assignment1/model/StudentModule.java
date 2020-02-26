package com.mcino.assignment1.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@IdClass(StudentModuleId.class)
public class StudentModule implements Serializable {

    @Id
    @Column(name = "student_id")
    private Long studentId;

    @Id
    @Column(name = "module_id")
    private Long moduleId;

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
