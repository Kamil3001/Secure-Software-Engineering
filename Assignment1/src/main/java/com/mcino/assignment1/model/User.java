package com.mcino.assignment1.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@MappedSuperclass
@Data
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Pattern(regexp = "^[\\p{L}’\\-]+$")
    String name;

    @NotNull
    @Pattern(regexp = "^[\\p{L}’\\-]+$")
    String surname;

    @NotNull
    @Pattern(regexp = "^[\\p{Alnum}]")
    String username;

    @NotNull
    @Pattern(regexp = "^[\\p{Alnum}]")
    String password;

}
