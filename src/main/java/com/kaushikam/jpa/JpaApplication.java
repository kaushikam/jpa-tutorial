package com.kaushikam.jpa;

import com.kaushikam.jpa.entity.Student;
import com.kaushikam.jpa.repository.CourseRepository;
import com.kaushikam.jpa.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {

	private StudentRepository studentRepository;

	private CourseRepository courseRepository;

	private EntityManager entityManager;

	@Autowired
	public JpaApplication(
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
		SpringApplication.run(JpaApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Student student = studentRepository.findById(20001L);
		logger.info("student name: {}", student.getName());
		//logger.info("Passport is {}", student.getPassport().getNumber());
	}
}
