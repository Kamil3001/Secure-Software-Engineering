package com.mcino.assignment1.exception;

public class StudentModuleNotFoundException extends Exception {
    public StudentModuleNotFoundException(long studentId, long moduleId) {
        super(String.format("No relationship found for Student ID: '%d' and Module ID: '%d'", studentId, moduleId));
    }
}
