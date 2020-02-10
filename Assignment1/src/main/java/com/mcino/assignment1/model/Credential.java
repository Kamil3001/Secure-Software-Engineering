package com.mcino.assignment1.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "credentials")
@Data
public class Credential {

    @Id
    @Pattern(regexp = "^[\\p{Alnum}]")
    private String username;

    @NotNull
    private String password;
}
