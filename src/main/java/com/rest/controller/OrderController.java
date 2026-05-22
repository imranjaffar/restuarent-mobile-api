package com.rest.controller;

import com.rest.dto.PlaceOrderRequest;
import com.rest.dto.UpdateStatusRequest;
import com.rest.entities.Order;
import com.rest.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // 🔥 PLACE ORDER
    @PostMapping("/place")
    public Order placeOrder(@RequestBody PlaceOrderRequest req) {
        return service.placeOrder(req);
    }

    // 🔥 GET ALL
    @GetMapping
    public List<Order> getAll() {
        return service.getAll();
    }

    // 🔥 GET BY ID
    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // 🔥 UPDATE STATUS
    @PostMapping("/status")
    public Order updateStatus(@RequestBody UpdateStatusRequest req) {
        return service.updateStatus(req.getId(), req.getStatus());
    }

    // 🔥 DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}