package com.apapedia.catalogue.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE catalog SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "catalog") 
public class Catalogue {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "harga", nullable = false)
    private BigDecimal price;

    @Column(name = "id_seller", nullable = false)
    private UUID sellerId;

    @Column(name = "nama_produk", nullable = false)
    private String productName;

    @Column(name = "desc_produk", nullable = false)
    private String productDesc;

    @Column(name = "stok", nullable = false)
    private int stock;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "is_deleted")
    private boolean isDeleted = Boolean.FALSE;

}