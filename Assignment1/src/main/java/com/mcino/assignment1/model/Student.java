package com.mcino.assignment1.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends User implements Serializable {

    @NotEmpty
    private String email;

    @NotEmpty
    private String address;

    @NotEmpty
    private String phoneNum;

    private boolean feePaid;

    @ToString.Exclude
//    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<StudentModule> studentModules = new HashSet<>();
}
