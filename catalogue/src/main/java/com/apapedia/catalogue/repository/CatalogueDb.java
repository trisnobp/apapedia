package com.apapedia.catalogue.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.apapedia.catalogue.model.Catalogue;

@Repository
public interface CatalogueDb extends JpaRepository<Catalogue, UUID> , JpaSpecificationExecutor<Catalogue> {
    List<Catalogue> findAllByOrderByProductNameAsc();

    List<Catalogue> findByProductNameContaining(String productName);

    List<Catalogue> findBySellerId(UUID sellerId);
    
}
