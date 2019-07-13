package com.kaushikam.hibernatejpain100steps.repository;

import com.kaushikam.hibernatejpain100steps.HibernateJpaIn100StepsApplication;
import com.kaushikam.hibernatejpain100steps.entity.Course;
import com.kaushikam.hibernatejpain100steps.entity.Student;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = HibernateJpaIn100StepsApplication.class)
public class StudentCourseTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void test1SaveCourseAndStudents() {
        Course course = courseRepository.findById(10002L);
        Student student1 = entityManager.find(Student.class, 20001L);
        Student student2 = entityManager.find(Student.class, 20002L);
        Set<Student> students = Stream.of(student1, student2).collect(Collectors.toSet());
        course.setStudents(students);
        courseRepository.save(course);

        Course foundCourse = courseRepository.findById(10002L);
        Assert.assertEquals(2, foundCourse.getStudents().size());

        Student foundStudent = entityManager.find(Student.class, 20001L);
        Assert.assertEquals(1, foundStudent.getCourses().size());
        Assert.assertTrue(foundStudent.getCourses().contains(foundCourse));
    }
}
