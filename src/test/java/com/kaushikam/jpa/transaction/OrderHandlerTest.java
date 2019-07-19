package com.kaushikam.jpa.transaction;

import com.kaushikam.jpa.JpaApplication;
import com.kaushikam.jpa.entity.transaction.products.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaApplication.class)
public class OrderHandlerTest {

    @Autowired
    private EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testPlaceOrderAndSave() {
        //Stock stock = entityManager.find(Stock.class, )
    }
}
