package com.kaushikam.jpa.repository;

import com.kaushikam.jpa.JpaApplication;
import com.kaushikam.jpa.entity.Course;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = JpaApplication.class)
public class CacheTest {

    @Autowired
    private CourseRepository courseRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testCachingStatus() {
        Course course = findCourse();
        logger.info("Found course: {}", course);
    }

    @Test
    public void testCachingStatusAgain() {
        Course course1 = findCourse();
        logger.info("Found course once again: {}", course1);
    }


    private Course findCourse() {
        return courseRepository.findById(10001L);
    }
}
