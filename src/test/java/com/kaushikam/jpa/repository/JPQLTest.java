package com.kaushikam.jpa.repository;

import com.kaushikam.jpa.JpaApplication;
import com.kaushikam.jpa.entity.Course;
import com.kaushikam.jpa.entity.Student;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = JpaApplication.class)
public class JPQLTest {

    @Autowired
    private EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testSelectWithoutStudents() {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c WHERE c.students is empty ", Course.class
        );
        List<Course> courses = query.getResultList();
        Assert.assertEquals(1, courses.size());
    }

    @Test
    public void testSelectCoursesWithAtLeast2Students() {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c WHERE size(c.students) >= 2", Course.class
        );
        List<Course> courses = query.getResultList();
        Assert.assertEquals(1, courses.size());
    }

    @Test
    public void testSelectCoursesOrderByNumberOfStudent() {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c order by size(c.students) asc", Course.class
        );
        List<Course> courses = query.getResultList();
        Assert.assertEquals("Spring Boot in 100 steps", courses.get(0).getName());
    }

    @Test
    public void testSelectStudentsByPassportWithAPattern() {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.passport.number LIKE '%A23%'", Student.class
        );
        List<Student> students = query.getResultList();
        Assert.assertEquals(2, students.size());
    }

    @Test
    public void testJoinReturnChildEntity() {
        Query query = entityManager.createQuery(
                "SELECT s FROM Course c JOIN c.students s"
        );
        List resultList = query.getResultList();
        resultList.forEach( c -> logger.info("Result -> {}", c));
    }

    @Test
    public void testJoinReturnChildEntityReview() {
        Query query = entityManager.createQuery(
                "SELECT r FROM Course c JOIN c.reviews r WHERE c.id=10001L"
        );
        List resultList = query.getResultList();
        resultList.forEach( c -> logger.info("Result -> {}", c));
    }

    @Test
    public void testJoin() {
        Query query = entityManager.createQuery(
                "SELECT c, s FROM Course c JOIN c.students s"
        );
        List resultList = query.getResultList();
        resultList.forEach( c -> logger.info("Result -> {}", c));
    }

    @Test
    public void testLeftJoin() {
        Query query = entityManager.createQuery(
                "SELECT c, s FROM Course c LEFT JOIN c.students s"
        );
        List resultList = query.getResultList();
        resultList.forEach( c -> logger.info("Result -> {}", c));
    }

    // Below will fail as there is no RIGHT JOIN in JPQL
    @Test
    public void testRightJoin() {
        Query query = entityManager.createQuery(
                "SELECT c, s FROM Course c RIGHT JOIN c.students s"
        );
        List resultList = query.getResultList();
        resultList.forEach( c -> logger.info("Result -> {}", c));
    }

    @Test
    public void testCrossJoin() {
        Query query = entityManager.createQuery(
                "SELECT c, s FROM Course c, Student s"
        );
        List resultList = query.getResultList();
        resultList.forEach( c -> logger.info("Result -> {}", c));
    }
}
