package com.mcino.assignment1.model;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class StudentModuleSpecs {
    public static Specification<StudentModule> getStudentModulesByStudentId(Long studentId) {
        return new Specification<StudentModule>() {
            @Override
            public Predicate toPredicate(Root<StudentModule> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }

    public static Specification<StudentModule> getStudentModulesByModuleId(Long moduleId) {
        return new Specification<StudentModule>() {
            @Override
            public Predicate toPredicate(Root<StudentModule> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }
}
