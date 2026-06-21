package com.rest.dto;



public class CreateTableRequest {

    private String tableNumber;

    private int capacity;


    public String getTableNumber() {
        return tableNumber;
    }


    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }


    public int getCapacity() {
        return capacity;
    }


    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
