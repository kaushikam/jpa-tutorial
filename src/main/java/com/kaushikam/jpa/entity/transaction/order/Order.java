package com.kaushikam.jpa.entity.transaction.order;

import com.kaushikam.jpa.entity.transaction.products.Product;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date orderedOn;

    @OneToMany(mappedBy = "order")
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        this.products.add(product);
        product.setOrder(this);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.setOrder(null);
    }

    public Double totalPrice() {
        return this.products.stream()
                .mapToDouble(Product::getPrice).sum();
    }
}
