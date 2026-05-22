package com.rest.repository;

import com.rest.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    // =========================
    // SEARCH
    // =========================
    List<MenuItem> findByNameContainingIgnoreCase(String name);

    // =========================
    // FILTER BY CATEGORY
    // =========================
    List<MenuItem> findByCategory(String category);

    // =========================
    // SORTING
    // =========================
    List<MenuItem> findAllByOrderByPriceAsc();

    List<MenuItem> findAllByOrderByPriceDesc();

    // =========================
    // COUNT BY CATEGORY (FOR DASHBOARD)
    // =========================
    long countByCategory(String category);

    // =========================
    // DISTINCT CATEGORIES (FOR DROPDOWN)
    // =========================
    @Query("SELECT DISTINCT m.category FROM MenuItem m")
    List<String> findDistinctCategories();
}