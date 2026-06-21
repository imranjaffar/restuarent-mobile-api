package com.rest.service;

import com.rest.dto.CreateTableRequest;
import com.rest.entities.RestaurantTable;
import com.rest.repository.RestaurantTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    private final RestaurantTableRepository repository;

    public TableService(
            RestaurantTableRepository repository) {
        this.repository = repository;
    }

    public List<RestaurantTable> findAll() {
        return repository.findAll();
    }


    public RestaurantTable createTable(
            CreateTableRequest request
    ){

        RestaurantTable table =new RestaurantTable(
                        request.getTableNumber(),
                        request.getCapacity()
                );


        return repository.save(table);
    }

    public RestaurantTable updateStatus(
            Long id,
            String status) {

        RestaurantTable table =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Table not found"));

        table.setStatus(status);

        return repository.save(table);
    }


    public void deleteTable(Long id){

        repository.deleteById(id);

    }


}
