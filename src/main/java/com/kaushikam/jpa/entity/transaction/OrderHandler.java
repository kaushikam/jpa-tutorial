package com.kaushikam.jpa.entity.transaction;

import com.kaushikam.jpa.entity.transaction.order.ProductOrder;
import com.kaushikam.jpa.entity.transaction.products.Product;
import com.kaushikam.jpa.entity.transaction.products.Stock;

import java.util.List;

public class OrderHandler {

    public ProductOrder addProducts(List<Product> products, ProductOrder existing) {
        ProductOrder order = existing != null ? existing : new ProductOrder();
        products.forEach(product -> {
            Stock stock = product.getStock();
            order.addProduct(stock.removeSingleProduct());
        });
        return order;
    }

    public ProductOrder removeProducts(List<Product> products, ProductOrder order) {
        products.forEach(product -> {
            Stock stock = product.getOldStock();
            order.removeProduct(product);
            stock.addProduct(product);
        });

        return order;
    }
}
