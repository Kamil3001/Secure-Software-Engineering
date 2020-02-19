package com.mcino.assignment1.exception;

public class ModuleNotFoundException extends Exception {
    public ModuleNotFoundException(long moduleId) {
        super(String.format("Module not found with ID: '%d'", moduleId));
    }
}
