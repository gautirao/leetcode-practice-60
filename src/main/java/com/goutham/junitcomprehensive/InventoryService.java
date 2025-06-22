package com.goutham.junitcomprehensive;

public interface InventoryService {
    boolean isInStock(String itemId, int quantity);
    void reserve(String itemId, int quantity);

}
