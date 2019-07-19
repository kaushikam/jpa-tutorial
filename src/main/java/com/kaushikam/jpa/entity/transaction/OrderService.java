package com.kaushikam.jpa.entity.transaction;

import com.kaushikam.jpa.entity.transaction.order.ProductOrder;
import com.kaushikam.jpa.entity.transaction.products.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {

    private EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public OrderService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public ProductOrder OrderProducts(List<Product> products, ProductOrder order) {
        OrderHandler orderHandler = new OrderHandler();
        order = orderHandler.addProducts(products, order);
        entityManager.persist(order);
        entityManager.flush();
        return order;
    }
}
