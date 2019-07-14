package com.kaushikam.jpa.repository;

import com.kaushikam.jpa.entity.inheritance.table_per_class.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class TeacherRepository {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private EntityManager entityManager;

    @Autowired
    public TeacherRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Teacher findById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    @Transactional
    public void save(Teacher teacher) {
        if (teacher.getId() == null) {
            this.entityManager.persist(teacher);
        } else {
            this.entityManager.merge(teacher);
        }
    }

    public List<Teacher> findAll() {
        return entityManager
                .createQuery("SELECT t FROM Teacher t", Teacher.class)
                .getResultList();
    }
}
