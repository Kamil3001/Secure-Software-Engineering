package com.mcino.assignment1.repository;

import com.mcino.assignment1.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long>, JpaSpecificationExecutor<Module> {

    List<Module> findAllByModuleCode(String moduleCode);

}
