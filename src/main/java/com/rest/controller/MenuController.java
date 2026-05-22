package com.rest.controller;

import com.rest.entities.MenuItem;
import com.rest.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@CrossOrigin("*")
public class MenuController {

    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    // =========================
    // GET ALL MENU ITEMS
    // =========================
    @GetMapping
    public List<MenuItem> getAllMenu() {
        return service.getAll();
    }

    // =========================
    // ADD MENU ITEM (REST STYLE FIXED)
    // =========================
    @PostMapping
    public MenuItem addItem(@RequestBody MenuItem item) {
        return service.add(item);
    }

    // =========================
    // UPDATE MENU ITEM (REST STYLE FIXED)
    // =========================
    @PutMapping("/{id}")
    public MenuItem updateItem(
            @PathVariable Long id,
            @RequestBody MenuItem item
    ) {
        return service.update(id, item);
    }

    // =========================
    // DELETE MENU ITEM (REST STYLE FIXED)
    // =========================
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        service.delete(id);
    }
}