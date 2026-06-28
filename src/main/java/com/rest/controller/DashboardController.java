package com.rest.controller;

import com.rest.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

        LocalDate today = LocalDate.now();

        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay().minusNanos(1);

        long totalOrders =
                orderRepo.countByOrderTimeBetween(start, end);

        Double totalRevenue =
                orderRepo.getRevenueBetween(start, end);

        long dineInCount =
                orderRepo.countByTypeAndOrderTimeBetween("DINE_IN", start, end);

        long takeawayCount =
                orderRepo.countByTypeAndOrderTimeBetween("TAKEAWAY", start, end);


//        long dineInCount =
//                orderRepo.countByType(
//                        "DINE_IN"
//                );
//
//        long takeawayCount =
//                orderRepo.countByType(
//                        "TAKEAWAY"
//                );

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
