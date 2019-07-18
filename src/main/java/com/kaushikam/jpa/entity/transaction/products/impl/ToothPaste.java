package com.kaushikam.jpa.entity.transaction.products.impl;

import com.kaushikam.jpa.entity.transaction.products.Product;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@ToString
@NoArgsConstructor
public class ToothPaste extends Product {

    private Double weight;
    private ToothPasteType type;

    public ToothPaste(Long id, String name, Date manufacturedOn,
                      Date expiry, String productCode, String batchNumber,
                      Double weight, ToothPasteType type) {
        super(id, name, manufacturedOn, expiry, productCode, batchNumber);
        this.weight = weight;
        this.type = type;
    }

    public enum ToothPasteType {
        GEL,
        NORMAL
    }
}
