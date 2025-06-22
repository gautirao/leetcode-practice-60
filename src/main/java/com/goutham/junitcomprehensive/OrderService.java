package com.goutham.junitcomprehensive;

public class OrderService {
    private final PaymentService paymentService;
    private final InventoryService inventoryService;

    public OrderService(PaymentService paymentService, InventoryService inventoryService) {
        this.paymentService = paymentService;
        this.inventoryService = inventoryService;
    }

    public boolean placeOrder(String itemId, int quantity, String userId) {
        if (!inventoryService.isInStock(itemId, quantity)) {
            return false;
        }
        boolean paymentSuccess = paymentService.charge(userId, itemId, quantity);
        if (paymentSuccess) {
            inventoryService.reserve(itemId, quantity);
            return true;
        }
        return false;
    }
}
