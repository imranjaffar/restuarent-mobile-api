package com.rest.dto;

import java.util.List;

public class PlaceOrderRequest {

    public String type;

    public List<Item> items;

    public static class Item {
        public Long id;
        public String name;
        public int qty;
        public double price;
    }
}