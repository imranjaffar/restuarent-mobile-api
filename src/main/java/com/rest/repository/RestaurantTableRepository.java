package com.rest.repository;

import com.rest.entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTableRepository
        extends JpaRepository<RestaurantTable, Long> {
}
