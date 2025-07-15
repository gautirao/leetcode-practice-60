package com.goutham.banking;

public enum Severity {
  HIGH(3),
  MEDIUM(2),
  LOW(1);

  final int value;

  Severity(int severityValue) {
    this.value = severityValue;
  }

  public int getValue() {
    return value;
  }
}
