package com.kaushikam.jpa.repository;

import com.kaushikam.jpa.JpaApplication;
import com.kaushikam.jpa.Question;
import com.kaushikam.jpa.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaApplication.class)
public class DeleteTest {

    @Autowired
    private EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    @Transactional
    public void testDeleteQuestionAlongWithAnswers() {
        Question question = entityManager.find(Question.class, 80001L);
        entityManager.remove(question);
        entityManager.flush();
    }

    @Test
    @Transactional
    public void testDeleteCourseAlongWithReviews() {
        Course course = entityManager.find(Course.class, 10001L);
        entityManager.remove(course);
        entityManager.flush();
    }
}