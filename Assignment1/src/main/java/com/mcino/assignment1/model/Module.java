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
    int id;

    @NotNull
    String name;

    @NotNull
    int coordinatorId;

    @NotNull
    String topics;

    @NotNull
    boolean isTerminated;

    @NotNull
    int capacity;

    @ManyToMany
    Set<Student> enrolledStudents;
}
