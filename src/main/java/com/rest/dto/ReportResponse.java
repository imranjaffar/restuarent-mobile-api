package com.rest.dto;

import java.util.List;
import java.util.Map;

public class ReportResponse {

    private double sales;
    private Map<String, Double> salesByType;
    private List<Object[]> topItems;

    public ReportResponse() {}

    public ReportResponse(double sales,
                          Map<String, Double> salesByType,
                          List<Object[]> topItems) {
        this.sales = sales;
        this.salesByType = salesByType;
        this.topItems = topItems;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public Map<String, Double> getSalesByType() {
        return salesByType;
    }

    public void setSalesByType(Map<String, Double> salesByType) {
        this.salesByType = salesByType;
    }

    public List<Object[]> getTopItems() {
        return topItems;
    }

    public void setTopItems(List<Object[]> topItems) {
        this.topItems = topItems;
    }
}