package com.mcino.assignment1.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "coordinators")
@Data
@EqualsAndHashCode(callSuper = true)
public class Coordinator extends User implements Serializable {

    @OneToMany(mappedBy = "coordinatorId")
    private Set<Module> modules;
}
