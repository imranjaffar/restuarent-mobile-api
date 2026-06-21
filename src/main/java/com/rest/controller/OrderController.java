package com.rest.controller;

import com.rest.dto.PlaceOrderRequest;
import com.rest.dto.UpdateStatusRequest;
import com.rest.entities.Order;
import com.rest.repository.OrderRepository;
import com.rest.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderService service;

    private final OrderRepository orderRepo;


    public OrderController(OrderService service, OrderRepository orderRepo) {
        this.service = service;
        this.orderRepo = orderRepo;
    }

    @GetMapping
    public List<Order> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable =
                PageRequest.of(page, size);

        return orderRepo
                .findAll(pageable)
                .getContent();
    }


    // 🔥 PLACE ORDER
    @PostMapping("/place")
    public Order placeOrder(@RequestBody PlaceOrderRequest req) {
        return service.placeOrder(req);
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