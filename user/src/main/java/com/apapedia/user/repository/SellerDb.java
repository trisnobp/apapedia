package com.apapedia.user.repository;

import com.apapedia.user.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@Repository
public interface SellerDb extends JpaRepository<Seller, UUID> {
}