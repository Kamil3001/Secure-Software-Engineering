package com.mcino.assignment1.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "modules")
@Data
public class Module {

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

    @ManyToMany
    private Set<Student> enrolledStudents;
}
