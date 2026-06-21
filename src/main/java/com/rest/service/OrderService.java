package com.rest.service;

import com.rest.dto.PlaceOrderRequest;
import com.rest.entities.Order;
import com.rest.entities.OrderItem;
import com.rest.entities.RestaurantTable;
import com.rest.repository.OrderRepository;
import com.rest.repository.RestaurantTableRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final RestaurantTableRepository tableRepository;


    public OrderService(OrderRepository orderRepo, RestaurantTableRepository tableRepository) {
        this.orderRepo = orderRepo;
        this.tableRepository = tableRepository;
    }

    // 🔥 PLACE ORDER
    public Order placeOrder(PlaceOrderRequest req) {

        Order order = new Order();
        if (req.tableId != null) {

            RestaurantTable table =
                    tableRepository.findById(req.tableId)
                            .orElseThrow();

            table.setStatus("OCCUPIED");

            order.setTable(table);

            tableRepository.save(table);
        }


        order.setType(req.type);
        order.setStatus("PENDING");

        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        for (PlaceOrderRequest.Item i : req.items) {

            OrderItem item = new OrderItem();
            item.setMenuItemId(i.id);
            item.setName(i.name);
            item.setQuantity(i.qty);
            item.setPrice(i.price);
            item.setOrder(order);

            total += i.qty * i.price;
            items.add(item);
        }
        order.setOrderTime(LocalDateTime.now());
        order.setItems(items);
        order.setTotalAmount(total);

        return orderRepo.save(order);
    }

    // 🔥 GET ALL ORDERS
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    // 🔥 GET BY ID
    public Order getById(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    // 🔥 UPDATE STATUS
    public Order updateStatus(Long id, String status) {

        Order order = orderRepo.findById(id).orElse(null);

        if (order != null) {
            order.setStatus(status);
            if (status.equalsIgnoreCase("PAID") && order.getTable() != null) {
                RestaurantTable table = order.getTable();
                table.setStatus("AVAILABLE");
                order.setTable(table);
                tableRepository.save(table);
            }

            return orderRepo.save(order);
        }

        return null;
    }

    // 🔥 DELETE
    public void delete(Long id) {
        orderRepo.deleteById(id);
    }
}