package com.kaushikam.jpa.repository;

import com.kaushikam.jpa.JpaApplication;
import com.kaushikam.jpa.entity.Course;
import com.kaushikam.jpa.entity.Review;
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
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = JpaApplication.class)
public class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testFindCourse() {
        Course course = courseRepository.findById(10002L);
        Assert.assertEquals(Long.valueOf(10002L), course.getId());
        Assert.assertEquals(2, course.getReviews().size());
    }

    @Test
    public void testDeleteCourse() {
        courseRepository.deleteById(10001L);
        Assert.assertNull(courseRepository.findById(10001L));
        Review review = entityManager.find(Review.class, 50004L);
        Assert.assertNull(review);
    }

    @Test
    @Transactional
    public void testSaveCourse() throws Exception {
        Course course = new Course("Blockchain - Getting Started",
                LocalDateTime.of(2019, 7, 9, 17, 30, 5, 10));
        courseRepository.save(course);
        Assert.assertNotNull(course);
    }

    @Test
    @Transactional
    public void testUpdateCourse() {
        Course course = courseRepository.findById(10002L);
        course.setName("Spring Boot in 100 steps - Updated");
        courseRepository.save(course);
        Assert.assertEquals("Spring Boot in 100 steps - Updated",
                courseRepository.findById(10002L).getName());
    }

    @Test
    public void test1ListAll() {
        List<Course> courses = courseRepository.findAll();
        Assert.assertEquals(3, courses.size());
    }

    @Test
    public void test2FindByName() {
        List<Course> courses = courseRepository.findByName("100");
        Assert.assertEquals(2, courses.size());
    }

    @Test
    public void test3FindFirstByName() {
        Course course = courseRepository.findFirstByName("100");
        Assert.assertEquals("Spring Boot in 100 steps", course.getName());
    }

    @Test
    @Transactional
    public void testSaveReviews() {
        Course course = courseRepository.findById(10002L);
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("5", "Wonderful"));
        reviews.add(new Review("3", "OKAY"));
        course.setReviews(reviews);
        courseRepository.save(course);

        Course foundCourse = courseRepository.findById(10002L);
        foundCourse.getReviews().forEach(review -> logger.info("Review -> {}", review));
        Assert.assertEquals(4, foundCourse.getReviews().size());
    }
}
