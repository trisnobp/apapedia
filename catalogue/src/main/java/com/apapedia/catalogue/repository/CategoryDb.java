package com.apapedia.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.apapedia.catalogue.model.Category;

import java.util.UUID;
import java.util.List;


@Repository
public interface CategoryDb extends JpaRepository<Category, UUID> , JpaSpecificationExecutor<Category> {
    List<Category> findAll();
    
}
