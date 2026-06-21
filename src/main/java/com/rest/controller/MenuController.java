package com.rest.controller;

import com.rest.entities.MenuItem;
import com.rest.repository.MenuItemRepository;
import com.rest.service.MenuService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@CrossOrigin("*") // allow Flutter app
public class MenuController {

    private final MenuService service;

    private final MenuItemRepository repo;


    public MenuController(MenuService service, MenuItemRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    // 🔥 GET ALL MENU ITEMS
    @GetMapping
    public List<MenuItem> getAllMenu(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable =
                PageRequest.of(page, size);

        return repo
                .findAll(pageable)
                .getContent();
    }

    // 🔥 ADD ITEM
    @PostMapping("/add")
    public MenuItem addItem(@RequestBody MenuItem item) {
        return service.add(item);
    }

    // 🔥 UPDATE ITEM
    @PutMapping("/update/{id}")
    public MenuItem updateItem(
            @PathVariable Long id,
            @RequestBody MenuItem item
    ) {
        return service.update(id, item);
    }

    // 🔥 DELETE ITEM
    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable Long id) {
        service.delete(id);
    }
}