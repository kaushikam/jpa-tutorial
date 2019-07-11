package com.kaushikam.hibernatejpain100steps.repository;

import com.kaushikam.hibernatejpain100steps.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class StudentRepository {

    private EntityManager entityManager;

    @Autowired
    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }
}
