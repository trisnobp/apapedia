package com.apapedia.catalogue.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apapedia.catalogue.model.Category;
import com.apapedia.catalogue.repository.CatalogueDb;
import com.apapedia.catalogue.repository.CategoryDb;

import jakarta.annotation.PostConstruct;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CatalogueDb catalogueDb;

    @Autowired
    CategoryDb categoryDb;


    private final List<Category.CategoryName> predefinedCategories = List.of(
        Category.CategoryName.AKSESORIS_FASHION,
        Category.CategoryName.BUKU_ALAT_TULIS,
        Category.CategoryName.ELEKTRONIK,
        Category.CategoryName.FASHION_BAYI_ANAK,
        Category.CategoryName.FASHION_MUSLIM,
        Category.CategoryName.FOTOGRAFI,
        Category.CategoryName.HOBI_KOLEKSI,
        Category.CategoryName.JAM_TANGAN,
        Category.CategoryName.PERAWATAN_KECANTIKAN,
        Category.CategoryName.MAKANAN_MINUMAN,
        Category.CategoryName.OTOMOTIF,
        Category.CategoryName.PERLENGKAPAN_RUMAH,
        Category.CategoryName.SOUVENIR_PARTY_SUPPLIES
    );

    @PostConstruct
    public void init() {
        initializeCategories();
    }

    private void initializeCategories() {
        predefinedCategories.forEach(categoryName -> {
            categoryDb.findByNamaCategory(categoryName)
                .orElseGet(() -> createCategory(categoryName));
        });
    }

    private Category createCategory(Category.CategoryName categoryName) {
        Category newCategory = new Category();
        newCategory.setNamaCategory(categoryName); 
        return categoryDb.save(newCategory);
    }
    
}
