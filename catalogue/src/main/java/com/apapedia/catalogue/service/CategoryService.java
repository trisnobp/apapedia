package com.apapedia.catalogue.service;

import java.util.List;

import com.apapedia.catalogue.model.Category;

public interface CategoryService {
    public void init();

    public List<Category> getAllCategories();
    
}
