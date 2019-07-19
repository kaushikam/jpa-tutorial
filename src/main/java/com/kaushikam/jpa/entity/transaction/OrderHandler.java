package com.kaushikam.jpa.entity.transaction;

import com.kaushikam.jpa.entity.transaction.order.Order;
import com.kaushikam.jpa.entity.transaction.products.Product;
import com.kaushikam.jpa.entity.transaction.products.Stock;

import java.util.List;

public class OrderHandler {

    public Order addProducts(List<Product> products, Order existing) {
        Order order = existing != null ? existing : new Order();
        products.forEach(product -> {
            Stock stock = product.getStock();
            order.addProduct(stock.removeSingleProduct());
        });
        return order;
    }

    public Order removeProducts(List<Product> products, Order order) {
        products.forEach(product -> {
            Stock stock = product.getOldStock();
            order.removeProduct(product);
            stock.addProduct(product);
        });

        return order;
    }
}
