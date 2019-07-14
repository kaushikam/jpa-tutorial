package com.kaushikam.jpa.repository;

import com.kaushikam.jpa.entity.inheritance.joined.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class AnimalRepository {

    private EntityManager entityManager;

    @Autowired
    public AnimalRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(Animal animal) {
        if (animal.getId() == null)
            entityManager.persist(animal);
        else
            entityManager.merge(animal);
    }
}
