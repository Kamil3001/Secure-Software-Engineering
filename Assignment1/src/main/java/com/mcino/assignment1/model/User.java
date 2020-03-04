package com.mcino.assignment1.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/** @noinspection WeakerAccess*/
@MappedSuperclass
@Data
public abstract class User implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String nationality;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Credential credentials;

}
