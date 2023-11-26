package com.apapedia.order.model;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name="cart_item")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    

    @Id
    @GeneratedValue( generator = "cart_item_sequence")
    @GenericGenerator(name = "Cart_item_sequence", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_cart_item", nullable = false)
    private UUID idCartItem ;
  
    @Column(name = "id_product", nullable = false)
    private UUID productId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cart", referencedColumnName = "id_cart")
    @JsonBackReference
    private Cart cart;

    @Column(name = "quantity", nullable = false)
    private Integer quantity ; 
}
