package com.apapedia.order.model;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="Order_item")
@Entity
public class OrderItem {

    @EmbeddedId
    @Column(name = "id_order_item")
    private OrderItemId orderItemId ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_order", referencedColumnName = "id_order")
    @JsonBackReference
    private Order order;

    @Column(name = "quantity")
    private Integer quantity ;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private long productPrice;

}


