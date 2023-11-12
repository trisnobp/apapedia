package com.apapedia.order.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="Order_item")
@Entity
public class OrderItem {

    @EmbeddedId
    private OrderItemId orderItemId;
    @Column(name = "id_product")
    private UUID productId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_order", referencedColumnName = "id_order")
    private Order order;

    @Column(name = "quantity")
    private Integer quantity ;

    @Column(name = "product_name")
    private Integer productName;

    @Column(name = "product_price")
    private Integer productPrice;
    


}


