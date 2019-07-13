package com.kaushikam.hibernatejpain100steps.repository;

import com.kaushikam.hibernatejpain100steps.entity.inheritance.mapped_super_class.Book;
import com.kaushikam.hibernatejpain100steps.entity.inheritance.mapped_super_class.Publication;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PublicationRepository {

    private EntityManager entityManager;

    public PublicationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Book> findAllBooks() {
        return entityManager
                .createQuery("SELECT b FROM Book B", Book.class)
                .getResultList();
    }
}
