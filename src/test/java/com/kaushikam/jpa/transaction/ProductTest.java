package com.kaushikam.jpa.transaction;

import com.kaushikam.jpa.JpaApplication;
import com.kaushikam.jpa.entity.transaction.products.Product;
import com.kaushikam.jpa.entity.transaction.products.impl.Soap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaApplication.class)
public class ProductTest {

    @Autowired
    private EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testListAllProducts() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> products = query.getResultList();
        Assert.assertEquals(5, products.size());
    }

    @Test
    @Transactional
    public void testSelectAllSoaps() {
        TypedQuery<Soap> query = entityManager.createQuery("SELECT s FROM Soap s ORDER BY s.stock.id ASC", Soap.class);
        List<Soap> soaps = query.getResultList();
        Assert.assertEquals(3, soaps.size());
        soaps.forEach(soap -> logger.info("Stock Id -> {}", soap.getStock().getId()));
    }
}
