package com.mcino.assignment1.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends User {

    @NotNull
    private String email;

    @NotNull
    private String address;

    @NotNull
    private String phoneNum;

    @NotNull
    private boolean isFeePaid;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<StudentModule> studentModules = new HashSet<>();
}
