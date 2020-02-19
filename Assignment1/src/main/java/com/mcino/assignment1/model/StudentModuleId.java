package com.mcino.assignment1.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentModuleId implements Serializable {
    private Student student;
    private Module module;
}
