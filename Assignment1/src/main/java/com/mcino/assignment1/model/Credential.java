package com.mcino.assignment1.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "credentials")
@Data
public class Credential implements Serializable {

    @Autowired
    private transient PasswordEncoder passwordEncoder;

    @Id
    @Pattern(regexp = "^[\\p{Alnum}]{8,}$")
    private String username;

    @NotEmpty
    private String password;

//    public void setPassword(String password) {
//        passwordEncoder.encode(password);
//    }
}
