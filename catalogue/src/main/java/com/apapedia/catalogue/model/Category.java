package com.apapedia.catalogue.model;

import java.util.List;
import java.util.UUID;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category") 
public class Category {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "nama", nullable = false)
    private String namaCategory;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Catalogue> catalogs;
    
}
