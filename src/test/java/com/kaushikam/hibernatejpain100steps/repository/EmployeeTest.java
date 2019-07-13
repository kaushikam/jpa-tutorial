package com.kaushikam.hibernatejpain100steps.repository;

import com.kaushikam.hibernatejpain100steps.HibernateJpaIn100StepsApplication;
import com.kaushikam.hibernatejpain100steps.entity.Employee;
import com.kaushikam.hibernatejpain100steps.entity.FullTimeEmployee;
import com.kaushikam.hibernatejpain100steps.entity.PartTimeEmployee;
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
@SpringBootTest(classes = HibernateJpaIn100StepsApplication.class)
public class EmployeeTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test1FindEmployee() {
        FullTimeEmployee employee = (FullTimeEmployee) employeeRepository.findById(60001L);
        Assert.assertEquals("Kaushik", employee.getName());
    }

    @Test
    public void test2SavePartTimeEmployee() {
        PartTimeEmployee employee = new PartTimeEmployee("Sunil", BigDecimal.valueOf(100));
        employeeRepository.save(employee);
        Assert.assertNotNull(employee.getId());
    }

    @Test
    public void test3FindAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        Assert.assertEquals(2, employees.size());
    }
}
