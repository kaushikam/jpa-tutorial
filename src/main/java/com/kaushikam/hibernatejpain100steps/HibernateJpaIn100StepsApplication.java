package com.kaushikam.hibernatejpain100steps;

import com.kaushikam.hibernatejpain100steps.entity.Course;
import com.kaushikam.hibernatejpain100steps.entity.Student;
import com.kaushikam.hibernatejpain100steps.repository.CourseRepository;
import com.kaushikam.hibernatejpain100steps.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class HibernateJpaIn100StepsApplication implements CommandLineRunner {

	private StudentRepository studentRepository;

	private CourseRepository courseRepository;

	private EntityManager entityManager;

	@Autowired
	public HibernateJpaIn100StepsApplication(
			StudentRepository studentRepository,
			CourseRepository courseRepository,
			EntityManager entityManager
	) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
		this.entityManager = entityManager;
	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(HibernateJpaIn100StepsApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Student student = studentRepository.findById(20001L);
		logger.info("student name: {}", student.getName());
		//logger.info("Passport is {}", student.getPassport().getNumber());
	}
}
