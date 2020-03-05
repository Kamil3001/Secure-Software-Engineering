package com.mcino.assignment1.repository;

import com.mcino.assignment1.model.Credential;
import com.mcino.assignment1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    Student findByEmail(String email);
    Student findByCredentials(Credential credential);
}
