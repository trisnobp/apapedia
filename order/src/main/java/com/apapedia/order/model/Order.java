package com.apapedia.order.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="orders")

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {

    
    @Id
    @GeneratedValue(generator = "order_sequence")
    @GenericGenerator(name = "order_sequence", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_order", nullable = false)
    private UUID idOrder ;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "status")
    private Integer status ;

    @Column(name = "total_price")
    private Integer totalPrice ;


    @Column(name = "customer")
    private  UUID customer ;


    @Column(name = "seller")
    private UUID seller;

    
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> listOrderItem;

}
