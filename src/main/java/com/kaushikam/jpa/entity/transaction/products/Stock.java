package com.kaushikam.jpa.entity.transaction.products;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@ToString(exclude = "products")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "stock", cascade = CascadeType.PERSIST)
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        this.products.add(product);
        product.setStock(this);
        product.setOldStock(null);
    }

    public void addProducts(List<Product> products) {
        products.forEach(this::addProduct);
    }

    public Product removeSingleProduct() {
        if (products.size() == 0)
            throw new RuntimeException("Insufficient quantity");

        Product product = products.get(0);
        this.removeProduct(product);
        return product;
    }

    private void removeProduct(Product product) {
        this.products.remove(product);
        product.setStock(null);
        product.setOldStock(this);
    }
}
