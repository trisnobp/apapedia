package com.apapedia.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    private UUID userId;

    @Column(name = "cart_id")
    private UUID cartId;
}
