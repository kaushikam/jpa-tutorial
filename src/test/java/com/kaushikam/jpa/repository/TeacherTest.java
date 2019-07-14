package com.kaushikam.jpa.repository;

import com.kaushikam.jpa.JpaApplication;
import com.kaushikam.jpa.entity.inheritance.table_per_class.PartTimeTeacher;
import com.kaushikam.jpa.entity.inheritance.table_per_class.Teacher;
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

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = JpaApplication.class)
public class TeacherTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void test1SaveTeacher() {
        PartTimeTeacher teacher = new PartTimeTeacher("Shan", BigDecimal.valueOf(100));
        teacherRepository.save(teacher);
        Assert.assertNotNull(teacher.getId());
        Assert.assertTrue(teacher.getId() > 0);
    }

    @Test
    public void test2FindAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        Assert.assertEquals(2, teachers.size());
    }
}
