package com.mcino.assignment1.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "coordinators")
@Data
public class Coordinator extends User {

    @OneToMany(mappedBy = "coordinatorId")
    private Set<Module> modules;
}
