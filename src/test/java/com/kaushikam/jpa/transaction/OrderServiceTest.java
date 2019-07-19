package com.kaushikam.jpa.transaction;

import com.kaushikam.jpa.JpaApplication;
import com.kaushikam.jpa.entity.transaction.OrderService;
import com.kaushikam.jpa.entity.transaction.order.ProductOrder;
import com.kaushikam.jpa.entity.transaction.products.impl.Soap;
import com.kaushikam.jpa.entity.transaction.products.impl.ToothPaste;
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
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaApplication.class)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    @Transactional
    public void testCreateOrder() {
        TypedQuery<Soap> soapQuery = entityManager
                .createQuery("SELECT s FROM Soap s ORDER BY s.stock.createdOn ASC",
                        Soap.class);

        Soap soap = soapQuery.setMaxResults(1).getSingleResult();

        TypedQuery<ToothPaste> pasteQuery = entityManager
                .createQuery("SELECT t FROM ToothPaste t ORDER BY t.stock.createdOn ASC",
                        ToothPaste.class);
        ToothPaste paste = pasteQuery.setMaxResults(1).getSingleResult();
        ProductOrder order = orderService.OrderProducts(Arrays.asList(soap, paste),
                null);

        List<ProductOrder> orders = entityManager.createQuery("SELECT o FROM ProductOrder o", ProductOrder.class).getResultList();
        Assert.assertEquals(1, orders.size());
        Assert.assertEquals(Integer.valueOf(2), orders.get(0).numberOfProducts());
    }
}
