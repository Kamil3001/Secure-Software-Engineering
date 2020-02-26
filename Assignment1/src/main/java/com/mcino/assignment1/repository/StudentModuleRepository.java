package com.mcino.assignment1.repository;

import com.mcino.assignment1.model.StudentModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentModuleRepository extends JpaRepository<StudentModule, Long>, JpaSpecificationExecutor<StudentModule> {
    List<StudentModule> findByStudentId(Long studentId);
    List<StudentModule> findByModuleId(Long moduleId);
    StudentModule findByStudentIdAndModuleId(Long studentId, Long moduleId);
}
