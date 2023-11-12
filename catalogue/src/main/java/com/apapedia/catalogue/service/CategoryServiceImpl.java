package com.apapedia.catalogue.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.apapedia.catalogue.model.Category;
import com.apapedia.catalogue.repository.CategoryDb;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    CategoryDb categoryDb;

    @Override
    public List<Category> getAllCategory(){
        return categoryDb.findAll();
    }
}
