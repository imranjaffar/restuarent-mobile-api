package com.rest.service;


import com.rest.entities.MenuItem;
import com.rest.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuItemRepository repo;

    public MenuService(MenuItemRepository repo) {
        this.repo = repo;
    }

    public List<MenuItem> getAll() {
        return repo.findAll();
    }

    public MenuItem add(MenuItem item) {
        return repo.save(item);
    }

    public MenuItem update(Long id, MenuItem item) {
        Optional<MenuItem> existing = repo.findById(id);

        if (existing.isPresent()) {
            MenuItem m = existing.get();
            m.setName(item.getName());
            m.setPrice(item.getPrice());
            return repo.save(m);
        }

        return null;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}