package com.mcino.assignment1.repository;

import com.mcino.assignment1.model.StudentModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentModuleRepository extends JpaRepository<StudentModule, Long>, JpaSpecificationExecutor<StudentModule> { }
