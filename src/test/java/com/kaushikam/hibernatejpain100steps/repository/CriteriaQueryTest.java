package com.kaushikam.hibernatejpain100steps.repository;

import com.kaushikam.hibernatejpain100steps.HibernateJpaIn100StepsApplication;
import com.kaushikam.hibernatejpain100steps.entity.Course;
import com.kaushikam.hibernatejpain100steps.entity.Passport;
import com.kaushikam.hibernatejpain100steps.entity.Review;
import com.kaushikam.hibernatejpain100steps.entity.Student;
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
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = HibernateJpaIn100StepsApplication.class)
public class CriteriaQueryTest {

    @Autowired
    private EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testFindAllCourses() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List<Course> courses = query.getResultList();
        Assert.assertEquals(3, courses.size());
    }

    @Test
    public void testSelectAllCoursesWithoutStudents() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        TypedQuery<Course> query = entityManager.createQuery(
            cq.select(courseRoot).where(cb.isEmpty(courseRoot.get("students")))
        );
        List<Course> courses = query.getResultList();
        Assert.assertEquals(1, courses.size());
    }

    @Test
    public void testSelectCoursesWithAtLeast2Students() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        TypedQuery<Course> query = entityManager.createQuery(
            cq.select(courseRoot).where(
                    cb.ge(cb.size(courseRoot.get("students")), 2))
        );
        List<Course> courses = query.getResultList();
        Assert.assertEquals(1, courses.size());
    }

    @Test
    public void testSelectCoursesOrderByNumberOfStudent() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        TypedQuery<Course> query = entityManager.createQuery(
            cq.select(courseRoot).orderBy(cb.asc(cb.size(courseRoot.get("students"))))
        );
        List<Course> courses = query.getResultList();
        Assert.assertEquals("Spring Boot in 100 steps", courses.get(0).getName());
    }

    @Test
    public void testSelectStudentsByPassportWithAPattern() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);
        TypedQuery<Student> query = entityManager.createQuery(
            criteriaQuery.select(studentRoot)
                    .where(criteriaBuilder.like(studentRoot.get("passport").get("number"), "%A23%"))
        );
        List<Student> students = query.getResultList();
        Assert.assertEquals(2, students.size());
    }

    @Test
    public void testSelectAllCourses() {
       CriteriaBuilder cb = entityManager.getCriteriaBuilder();
       CriteriaQuery<Course> cq = cb.createQuery(Course.class);
       Root<Course> courseRoot = cq.from(Course.class);
       TypedQuery<Course> query = entityManager.createQuery(
               cq.select(courseRoot)
       );
       List<Course> courses = query.getResultList();
       Assert.assertEquals(3, courses.size());
    }

    @Test
    public void testSelectCourseWithASpecificIdWithPredicate() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        Predicate idCondition = cb.equal(courseRoot.get("id"), 10001L);
        TypedQuery<Course> query = entityManager.createQuery(
            cq.select(courseRoot).where(idCondition)
        );
        Course course = query.getSingleResult();
        Assert.assertEquals("JPA in 50 steps", course.getName());
    }

    @Test
    public void testSelectCourseWithASpecificIdWithoutPredicate() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        TypedQuery<Course> query = entityManager.createQuery(
                cq.select(courseRoot).where(
                        cb.equal(courseRoot.get("id"), 10001L)
                )
        );
        Course course = query.getSingleResult();
        Assert.assertEquals("JPA in 50 steps", course.getName());
    }

    @Test
    public void testSelectCourseWithASpecificNamePattern() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        TypedQuery<Course> query = entityManager.createQuery(
                cq.select(courseRoot).where(
                        cb.like(courseRoot.get("name"), "%JPA%")
                )
        );
        Course course = query.getSingleResult();
        Assert.assertEquals("JPA in 50 steps", course.getName());
    }

    @Test
    public void testSelectCoursesWithCombiningConditionsWithAnd() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        TypedQuery<Course> query = entityManager.createQuery(
            cq.select(courseRoot).where(
                    cb.and(
                            cb.like(courseRoot.get("name"), "%100%"),
                            cb.gt(courseRoot.get("id"), 10001L),
                            cb.lt(courseRoot.get("id"), 10003L)
                    )
            )
        );
        Course course = query.getSingleResult();
        Assert.assertEquals("Spring Boot in 100 steps", course.getName());
    }

    @Test
    public void testSelectCoursesWithBetweenCondition() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        TypedQuery<Course> query = entityManager.createQuery(
                cq.select(courseRoot).where(
                    cb.between(courseRoot.get("id"), 10001L, 10003L)
                )
        );
        List<Course> courses = query.getResultList();
        Assert.assertEquals(3, courses.size());
    }

    @Test
    public void testSelectReviewsOrderByRatingAndId() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> cq = cb.createQuery(Review.class);
        Root<Review> reviewRoot = cq.from(Review.class);
        TypedQuery<Review> query = entityManager.createQuery(
            cq.select(reviewRoot).orderBy(
                cb.asc(reviewRoot.get("id")),
                cb.desc(reviewRoot.get("rating"))
            )
        );
        List<Review> reviews = query.getResultList();
        reviews.forEach(review -> logger.info("Review -> {}", review));
    }

    @Test
    public void testSelectCountOfCoursesAvailable() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Course> courseRoot = cq.from(Course.class);
        TypedQuery<Long> query = entityManager.createQuery(
            cq.select(
                cb.count(courseRoot)
            )
        );
        Long count = query.getSingleResult();
        Assert.assertEquals(Long.valueOf(3L), count);
    }

    @Test
    public void testSelectMaxOfCoursesId() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Course> courseRoot = cq.from(Course.class);
        TypedQuery<Long> query = entityManager.createQuery(
                cq.select(
                        cb.max(courseRoot.get("id"))
                )
        );
        Long max = query.getSingleResult();
        Assert.assertEquals(Long.valueOf(10003L), max);
    }

    @Test
    public void testSelectUsingInClause() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        CriteriaBuilder.In<String> inClause = cb.in(courseRoot.get("name"));
        Arrays.asList("Spring Boot in 100 steps",
                "Java web services in 100 steps")
                .forEach(inClause::value);
        TypedQuery<Course> query = entityManager.createQuery(
            cq.select(courseRoot).where(inClause)
        );
        List<Course> courses = query.getResultList();
        Assert.assertEquals(2, courses.size());
    }

    @Test
    public void testSelectUsingInExpression() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        CriteriaBuilder.In<String> inClause = cb.in(courseRoot.get("name"));
        List<String> titles = Arrays.asList("Spring Boot in 100 steps",
                "Java web services in 100 steps");
        TypedQuery<Course> query = entityManager.createQuery(
                cq.select(courseRoot).where(
                        courseRoot.get("name").in(titles)
                )
        );
        List<Course> courses = query.getResultList();
        Assert.assertEquals(2, courses.size());
    }

    @Test
    public void testSelectJoinCourseAndReview() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> cq = cb.createQuery(Review.class);
        Root<Course> courseRoot = cq.from(Course.class);
        TypedQuery<Review> query = entityManager.createQuery(
            cq.select(courseRoot.join("reviews"))
        );

        List<Review> reviews = query.getResultList();
        reviews.forEach(review -> logger.info("Passport -> {}", review));
        Assert.assertEquals(4, reviews.size());
    }

    @Test
    public void testSelectJoinCourseAndStudentAndGetStudent() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Course> courseRoot = cq.from(Course.class);
        TypedQuery<Student> query = entityManager.createQuery(
            cq.select(courseRoot.join("students"))
        );
        List<Student> students = query.getResultList();
        students.forEach(student -> logger.info("Student -> {}", student));
    }

    @Test
    public void testSelectJoinCourseAndStudentAndGetBoth() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Course> courseRoot = cq.from(Course.class);
        TypedQuery<Tuple> query = entityManager.createQuery(
            cq.select(cb.tuple(courseRoot, courseRoot.get("students")))
        );
        List<Tuple> tupleList = query.getResultList();
        tupleList.forEach(tuple -> logger.info("Tuple -> {}", tuple.toArray()));
    }

}
