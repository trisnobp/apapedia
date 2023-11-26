package com.apapedia.catalogue.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @Enumerated(EnumType.STRING)
    private CategoryName namaCategory;

    // gilang : mencegah error ketika berkomunikasi dengan microservice lain menggunakan Restful API
    @JsonManagedReference
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Catalogue> catalogs;
    
    public enum CategoryName {
        AKSESORIS_FASHION("Aksesoris Fashion"),
        BUKU_ALAT_TULIS("Buku & Alat Tulis"),
        ELEKTRONIK("Elektronik"),
        FASHION_BAYI_ANAK("Fashion Bayi & Anak"),
        FASHION_MUSLIM("Fashion Muslim"),
        FOTOGRAFI("Fotografi"),
        HOBI_KOLEKSI("Hobi & Koleksi"),
        JAM_TANGAN("Jam Tangan"),
        PERAWATAN_KECANTIKAN("Perawatan & Kecantikan"),
        MAKANAN_MINUMAN("Makanan & Minuman"),
        OTOMOTIF("Otomotif"),
        PERLENGKAPAN_RUMAH("Perlengkapan Rumah"),
        SOUVENIR_PARTY_SUPPLIES("Souvenir & Party Supplies");

        private final String displayName;

        CategoryName(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }

        
    }
}
