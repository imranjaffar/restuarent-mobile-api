package com.rest.controller;

import com.rest.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin("*")
public class DashboardController {

    private final OrderRepository orderRepo;

    public DashboardController(
            OrderRepository orderRepo
    ) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {

        Map<String, Object> res =
                new HashMap<>();

        long totalOrders =
                orderRepo.count();

        Double totalRevenue =
                orderRepo.getTotalRevenue();

        long dineInCount =
                orderRepo.countByType(
                        "DINE_IN"
                );

        long takeawayCount =
                orderRepo.countByType(
                        "TAKEAWAY"
                );

        res.put(
                "totalOrders",
                totalOrders
        );

        res.put(
                "totalRevenue",
                totalRevenue != null
                        ? totalRevenue
                        : 0
        );

        res.put(
                "dineInCount",
                dineInCount
        );

        res.put(
                "takeawayCount",
                takeawayCount
        );

        return res;
    }
}
