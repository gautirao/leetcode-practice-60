package com.goutham.banking;

import java.time.Instant;

public record RiskEvent(
    long eventId, Severity severity, Instant timestamp, double score, String entity) {}
