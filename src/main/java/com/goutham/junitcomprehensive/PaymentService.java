package com.goutham.junitcomprehensive;

public interface PaymentService {
    boolean charge(String userId, String itemId, int quantity);

}
