package com.goutham.junitcomprehensive;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
  @Mock PaymentService paymentService;

  @Mock InventoryService inventoryService;

  @InjectMocks OrderService orderService;

  @Captor ArgumentCaptor<String> stringCaptor;
  @Captor ArgumentCaptor<Integer> intCaptor;

  @Test
  void testSuccessfullOrder() {
    when(inventoryService.isInStock("item1", 2)).thenReturn(true);
    when(paymentService.charge("user1", "item1", 2)).thenReturn(true);

    boolean result = orderService.placeOrder("item1", 2, "user1");
    assertTrue(result);
    verify(inventoryService, times(1)).isInStock("item1", 2);
    verify(paymentService, times(1)).charge("user1", "item1", 2);
    verify(inventoryService, times(1)).reserve("item1", 2);
  }

  @Test
  void noOrderPlacedDueToLowStock() {
    when(inventoryService.isInStock("item1", 2)).thenReturn(false);
    boolean result = orderService.placeOrder("item1", 2, "user1");
    assertFalse(result);
    verify(inventoryService, times(1)).isInStock("item1", 2);
    verifyNoInteractions(paymentService);
  }

  @Test
  void shouldFailDueToPaymentFailure() {
    when(inventoryService.isInStock("item1", 2)).thenReturn(true);
    when(paymentService.charge("user1", "item1", 2)).thenReturn(false);

    boolean result = orderService.placeOrder("item1", 2, "user1");
    assertFalse(result);
    verify(inventoryService, times(1)).isInStock("item1", 2);
    verify(paymentService, times(1)).charge("user1", "item1", 2);
    verify(inventoryService, never()).reserve(anyString(), anyInt());
  }

  @Test
  void shouldUsePassedValuesWhenPlacingOrder() {
    when(inventoryService.isInStock(anyString(), anyInt())).thenReturn(true);
    when(paymentService.charge(anyString(), anyString(), anyInt())).thenReturn(true);

    boolean result = orderService.placeOrder("item1", 2, "user1");
    assertTrue(result);
    verify(paymentService)
        .charge(stringCaptor.capture(), stringCaptor.capture(), intCaptor.capture());
    assertEquals("user1", stringCaptor.getAllValues().get(0));
    assertEquals("item1", stringCaptor.getAllValues().get(1));
    assertEquals(2, intCaptor.getValue());
  }
}
