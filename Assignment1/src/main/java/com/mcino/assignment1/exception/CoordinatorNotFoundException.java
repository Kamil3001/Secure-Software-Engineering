package com.mcino.assignment1.exception;

public class CoordinatorNotFoundException extends Exception {
    public CoordinatorNotFoundException(long coordinatorId) {
        super(String.format("Coordinator not found with ID: '%d'", coordinatorId));
    }
}
