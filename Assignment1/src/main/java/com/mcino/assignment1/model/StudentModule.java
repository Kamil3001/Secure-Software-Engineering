package com.mcino.assignment1.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@IdClass(StudentModuleId.class)
public class StudentModule implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn
    private Module module;

    private String grade;
}
