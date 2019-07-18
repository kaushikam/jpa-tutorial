package com.kaushikam.jpa;

import com.kaushikam.jpa.entity.transaction.products.Stock;
import com.kaushikam.jpa.entity.transaction.products.impl.Soap;
import com.kaushikam.jpa.entity.transaction.products.impl.ToothPaste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {

	private EntityManager entityManager;

	@Autowired
	public JpaApplication(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void run(String... args) {
		ToothPaste toothPaste1 = new ToothPaste(null, "Colgate", new Date(), new Date(), "ASGE", "1111EF", 100.0, ToothPaste.ToothPasteType.NORMAL);
		ToothPaste toothPaste2 = new ToothPaste(null, "Colgate", new Date(), new Date(), "ASEE", "1111EF", 100.0, ToothPaste.ToothPasteType.NORMAL);
		Stock stock = new Stock();
		stock.returnProducts(Arrays.asList(toothPaste1, toothPaste2));
		entityManager.persist(stock);

		Soap soap1 = new Soap(null, "Lux", new Date(), new Date(), "HDGF", "LL123", 100.0, Soap.Smell.LAVENDER);
		Soap soap2 = new Soap(null, "Lux", new Date(), new Date(), "HDGF", "LL123", 100.0, Soap.Smell.LAVENDER);
		Stock stock1 = new Stock();
		stock.returnProducts(Arrays.asList(soap1, soap2));
		entityManager.persist(stock);

		Soap soap3 = new Soap(null, "Lux", new Date(), new Date(), "HDGF", "LL124", 100.0, Soap.Smell.LAVENDER);
		Stock stock2 = new Stock();
		stock.returnProducts(Arrays.asList(soap3));
		entityManager.persist(stock);
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	/*private StudentRepository studentRepository;

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

	}*/
}
