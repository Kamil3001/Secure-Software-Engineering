package com.mcino.assignment1.exception;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(long studentId) {
        super(String.format("Student not found with ID: '%d'", studentId));
    }
}
