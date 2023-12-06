package com.apapedia.catalogue.service;

import java.util.List;

import com.apapedia.catalogue.DTO.request.CreateCatalogueRequestDTO;
import com.apapedia.catalogue.DTO.response.CategoryDetailDTO;
import com.apapedia.catalogue.DTO.response.ResponseCatalogueDTO;
import com.apapedia.catalogue.model.Category;

public interface CategoryService {
    public void init();

    public List<CategoryDetailDTO> getAllCategories();
    
}
