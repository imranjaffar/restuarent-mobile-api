package com.rest.service;

import com.rest.dto.ReportResponse;
import com.rest.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final OrderRepository orderRepo;

    public ReportService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public ReportResponse getReport() {

        // 🔥 total sales
        Double sales = orderRepo.getTotalRevenue();
        if (sales == null) sales = 0.0;

        // 🔥 sales by type
        List<Object[]> typeData = orderRepo.getSalesByType();

        Map<String, Double> salesByType = new HashMap<>();

        for (Object[] row : typeData) {
            salesByType.put(
                    row[0].toString(),
                    (Double) row[1]
            );
        }

        // 🔥 top items
        List<Object[]> topItems = orderRepo.getTopItems();

        return new ReportResponse(sales, salesByType, topItems);
    }


    public ReportResponse getReportByDateRange(
            LocalDate startDate,
            LocalDate endDate
    ) {

        LocalDateTime start =
                startDate.atStartOfDay();

        LocalDateTime end =
                endDate.atTime(23,59,59);

        Double sales =
                orderRepo.getTotalSalesBetween(
                        start,
                        end
                );

        if (sales == null) {
            sales = 0.0;
        }

        List<Object[]> typeData =
                orderRepo.getSalesByTypeBetween(
                        start,
                        end
                );

        Map<String, Double> salesByType =
                new HashMap<>();

        for (Object[] row : typeData) {

            salesByType.put(
                    row[0].toString(),
                    ((Number) row[1]).doubleValue()
            );
        }

        List<Object[]> topItems =
                orderRepo.getTopItemsBetween(
                        start,
                        end
                );

        return new ReportResponse(
                sales,
                salesByType,
                topItems
        );
    }
}