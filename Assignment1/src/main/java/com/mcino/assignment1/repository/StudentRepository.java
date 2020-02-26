package com.mcino.assignment1.repository;

import com.mcino.assignment1.model.Student;
import com.mcino.assignment1.model.StudentModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    Student findByEmail(String email);
}
