package com.rest.repository;

import com.rest.entities.Order;
import com.rest.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // =========================
    // BASIC QUERIES
    // =========================


    List<OrderItem> findByOrder(Order order);

    // =========================
    // TOP SELLING ITEMS
    // =========================



    // =========================
    // TOTAL REVENUE FROM ITEMS
    // =========================

    @Query("""
        SELECT COALESCE(SUM(oi.price * oi.quantity), 0)
        FROM OrderItem oi
    """)
    double getTotalItemRevenue();
}