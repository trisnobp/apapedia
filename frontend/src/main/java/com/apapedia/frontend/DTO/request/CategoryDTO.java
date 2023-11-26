package com.apapedia.frontend.DTO.request;


import java.util.List;
import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDTO {
    
    private UUID id = UUID.randomUUID();

   
    @Enumerated(EnumType.STRING)
    private CategoryName namaCategory;

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
