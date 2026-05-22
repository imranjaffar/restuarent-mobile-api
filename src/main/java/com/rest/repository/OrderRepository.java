package com.rest.repository;

import com.rest.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // =========================
    // BASIC FILTERS
    // =========================

    List<Order> findByType(String type);

    List<Order> findByStatus(String status);

    List<Order> findByOrderTimeBetween(LocalDateTime start, LocalDateTime end);

    // =========================
    // DASHBOARD STATS
    // =========================

    long countByType(String type);

    long countByStatus(String status);

    // =========================
    // TOTAL REVENUE
    // =========================





    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o")
    double getTotalRevenue();

    // =========================
    // REVENUE BY DATE RANGE
    // =========================

    @Query("""
        SELECT COALESCE(SUM(o.totalAmount), 0)
        FROM Order o
        WHERE o.orderTime BETWEEN :start AND :end
    """)
    double getRevenueBetween(LocalDateTime start, LocalDateTime end);



    @Query("SELECT SUM(o.totalAmount) FROM Order o")
    Double getTotalSales();

    @Query("SELECT o.type, SUM(o.totalAmount) FROM Order o GROUP BY o.type")
    List<Object[]> getSalesByType();

    @Query("""
        SELECT menuItemId, SUM(i.quantity)
        FROM OrderItem i
        GROUP BY menuItemId
        ORDER BY SUM(i.quantity) DESC
    """)
    List<Object[]> getTopItems();
}