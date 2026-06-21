package com.rest.controller;

import com.rest.dto.CreateTableRequest;
import com.rest.entities.RestaurantTable;
import com.rest.service.TableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/tables")
public class TableController {

    private final TableService service;

    public TableController(TableService service) {
        this.service = service;
    }

    @GetMapping
    public List<RestaurantTable> getAll() {
        return service.findAll();
    }

    @PutMapping("/{id}/status")
    public RestaurantTable updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return service.updateStatus(id, status);
    }

    // CREATE TABLE

    @PostMapping("/create")
    public RestaurantTable create(
            @RequestBody CreateTableRequest request
    ){

        return service.createTable(request);

    }



    // DELETE

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ){

        service.deleteTable(id);

    }
}
