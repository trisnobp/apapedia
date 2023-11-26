package com.apapedia.order.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderItemId implements Serializable {
    private UUID orderId;
    private UUID productId;

    // Constructors, getters, setters, and equals/hashCode methods
    // Be sure to implement equals and hashCode using both fields
}
