package com.apapedia.catalogue.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.apapedia.catalogue.model.Catalogue;

// @Repository
// public interface CatalogueDb extends JpaRepository<Catalogue, UUID> , JpaSpecificationExecutor<Catalogue> {
//     List<Catalogue> findAllByOrderByJudulAsc();
    
// }

@Repository
public interface CatalogueDb extends JpaRepository<Catalogue, UUID> , JpaSpecificationExecutor<Catalogue> {
    List<Catalogue> findAllByOrderByProductNameAsc();

    List<Catalogue> findByStockNotOrderByProductNameAsc(int stock);

    List<Catalogue> findByProductNameContainingIgnoreCase(String productName);

    List<Catalogue> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

    List<Catalogue> findBySellerId(UUID sellerId);

    List<Catalogue> findByOrderByPriceAsc();

    List<Catalogue> findByOrderByPriceDesc();

    List<Catalogue> findByOrderByProductNameAsc();

    List<Catalogue> findByOrderByProductNameDesc();

    
}
