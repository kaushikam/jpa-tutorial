package com.kaushikam.jpa.entity.transaction.products.impl;

import com.kaushikam.jpa.entity.transaction.products.Product;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
public class Soap extends Product {

    private Double weight;
    private Smell smell;

    public Soap(Long id, String name, Date manufacturedOn, Date expiry, String productCode, String batchNumber, Double weight, Smell smell) {
        super(id, name, manufacturedOn, expiry, productCode, batchNumber);
        this.weight = weight;
        this.smell = smell;
    }

    public enum Smell {
        LAVENDER,
        JASMIN
    }
}
