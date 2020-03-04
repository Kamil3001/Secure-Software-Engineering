package com.mcino.assignment1.repository;

import com.mcino.assignment1.model.Coordinator;
import com.mcino.assignment1.model.Credential;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long>, JpaSpecificationExecutor<Coordinator> {
    Coordinator findByCredentials(Credential credential);

}
