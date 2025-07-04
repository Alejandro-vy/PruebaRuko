package com.example.PruebaRuko.model;


import java.time.LocalDateTime;


public class ClientVisitStreak {

    private String clientId;
    private String storeId;
    private LocalDateTime start;
    private LocalDateTime end;

    public ClientVisitStreak(String clientId, String storeId, LocalDateTime start, LocalDateTime end) {
        this.clientId = clientId;
        this.storeId = storeId;
        this.start = start;
        this.end = end;
    }

    public String getClientId() {
        return clientId;
    }

    public String getStoreId() {
        return storeId;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Client " + clientId + " got a benefit at store " + storeId + " (from " + start + " to " + end + ")";
    }
}
