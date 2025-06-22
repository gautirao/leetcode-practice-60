package com.goutham.time;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Order {
  private String id;
  private LocalDate orderDate; // user-friendly date
  private LocalDateTime deliveryTime; // date + time (no zone)
  private Instant createdAt; // UTC timestamp
  private ZoneId customerZone; // timezone (optional)

  public Order(
      String id,
      LocalDate orderDate,
      LocalDateTime deliveryTime,
      Instant createdAt,
      ZoneId customerZone) {
    this.id = id;
    this.orderDate = orderDate;
    this.deliveryTime = deliveryTime;
    this.createdAt = createdAt;
    this.customerZone = customerZone;
  }

  public ZonedDateTime getZonedDeliveryTime() {
    return deliveryTime.atZone(customerZone);
  }

  public Duration timeSinceCreated() {
    return Duration.between(createdAt, Instant.now());
  }

  public boolean isOlderThanDays(long days) {
    return createdAt.isBefore(Instant.now().minus(days, ChronoUnit.DAYS));
  }

  public boolean isDeliveryInFuture() {
    return getZonedDeliveryTime().isAfter(ZonedDateTime.now(customerZone));
  }

  public long daysUntilDelivery() {
    return ChronoUnit.DAYS.between(LocalDate.now(customerZone), deliveryTime);
  }

  public boolean isDeliverywithinBusinessHOurs() {
    int hour = deliveryTime.getHour();
    return hour >= 9 && hour <= 17;
  }
}
