package com.apapedia.order.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name="cart")
@SQLDelete(sql = "UPDATE cart SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue( generator = "cart_sequence")
    @GenericGenerator(name = "cart_sequence", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_cart", nullable = false)

    private UUID idCart;

    @Column(name = "id_user", nullable = false)
    private UUID userId;

    @Column(name = "total_price", nullable = false)
    private long totalPrice;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonManagedReference
    private List<CartItem> listCartItem;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
