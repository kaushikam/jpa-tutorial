package com.kaushikam.jpa.repository;

import com.kaushikam.jpa.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CourseRepository {

    private EntityManager entityManager;

    @Autowired
    public CourseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Transactional
    public void deleteById(Long id) {
        Course course = findById(id);
        entityManager.remove(course);
    }

    @Transactional
    public Course save(Course course) {
        if (course.getId() != null) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }

        return course;
    }

    public List<Course> findAll() {
        TypedQuery<Course> query = entityManager
                .createNamedQuery("findAll", Course.class);
        return query.getResultList();
    }

    public List<Course> findByName(String name) {
        String queryString = String.format("SELECT c FROM Course c WHERE c.name LIKE '%%%s%%'", name);
        TypedQuery<Course> query = entityManager
                .createQuery(queryString, Course.class);
        return query.getResultList();
    }

    public Course findFirstByName(String name) {
        String queryString = String.format("", name);
        Query query = entityManager
                .createNativeQuery("SELECT * FROM course WHERE name LIKE ? LIMIT 1", Course.class);
        query.setParameter(1, "%" + name + "%");
        return (Course) query.getSingleResult();
    }

    public int updateStartedDate(LocalDateTime date) {
        Query query = entityManager.createNativeQuery("update course set started_date=to_timestamp(?, 'dd-MM-YYYY')", Course.class);
        String dateAsString = String.format("%s-%s-%s", date.getDayOfMonth(), date.getMonthValue(), date.getYear());
        System.out.println("date is " + dateAsString);
        query.setParameter(1, dateAsString);
        return query.executeUpdate();
    }
}
