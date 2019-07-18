package com.kaushikam.jpa.entity.transaction.products;

import com.kaushikam.jpa.entity.transaction.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString(exclude = {"stock", "oldStock"})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date manufacturedOn;
    private Date expiry;
    private String productCode;
    private String batchNumber;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "old_stock_id")
    private Stock oldStock;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    public Product(Long id, String name, Date manufacturedOn,
                   Date expiry, String productCode, String batchNumber) {
        this.id = id;
        this.name = name;
        this.manufacturedOn = manufacturedOn;
        this.expiry = expiry;
        this.productCode = productCode;
        this.batchNumber = batchNumber;
    }
}
