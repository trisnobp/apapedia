package com.apapedia.order.repository;

import java.util.UUID;

import com.apapedia.order.dto.response.TopProductsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apapedia.order.model.Cart;
import com.apapedia.order.model.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Repository
@Transactional
public interface OrderDb extends JpaRepository<Order,UUID> {

    Order findByIdOrder(UUID idOrder);

    @Query("SELECT o FROM Order o WHERE o.customer = :customer order by o.customer")
    List<Order> findByCustomer(@Param("customer") UUID customer);

    List<Order> findBySeller(UUID sellerId);

    @Query("SELECT oi.productName AS productName, SUM(oi.quantity) AS orderItemCount " +
            "FROM OrderItem oi " +
            "JOIN oi.order o " +
            "WHERE o.seller = :sellerId " +
            "AND MONTH(o.createdAt) = MONTH(CURRENT_DATE) " +
            "AND YEAR(o.createdAt) = YEAR(CURRENT_DATE) " +
            "GROUP BY oi.productName " +
            "ORDER BY orderItemCount DESC")
    List<TopProductsDTO> findProductNamesAndCounts(@Param("sellerId") UUID sellerId);
} 