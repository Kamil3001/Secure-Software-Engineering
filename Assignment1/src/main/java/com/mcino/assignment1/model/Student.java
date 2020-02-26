package com.mcino.assignment1.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends User implements Serializable {

    @NotNull
    private String email;

    @NotNull
    private String address;

    @NotNull
    private String phoneNum;

    @NotNull
    private boolean isFeePaid;

    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<StudentModule> studentModules = new HashSet<>();
}
