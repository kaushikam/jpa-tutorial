package com.kaushikam.hibernatejpain100steps;

import com.kaushikam.hibernatejpain100steps.entity.Course;
import com.kaushikam.hibernatejpain100steps.repository.CourseRepository;
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

	private CourseRepository courseRepository;

	private EntityManager entityManager;

	@Autowired
	public HibernateJpaIn100StepsApplication(
			CourseRepository courseRepository,
			EntityManager entityManager
	) {
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
		this.courseRepository.updateStartedDate(
				LocalDateTime.of(2019, 7, 1, 10, 10, 10)
		);
	}
}
