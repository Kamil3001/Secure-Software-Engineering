package com.mcino.assignment1.service;

import com.mcino.assignment1.exception.StudentNotFoundException;
import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyProfileService {

    @Autowired
    StudentRepository studentRepository;

    public Student retrieveStudent(long id) throws StudentNotFoundException {
        return studentRepository.findById(id)
                .orElseThrow( () ->new StudentNotFoundException(id));
    }
}
