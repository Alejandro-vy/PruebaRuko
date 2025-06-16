package com.example.PruebaRuko.model;

import java.util.List;

public class TransactionSummary {
    private String clientId;
    private List<WeeklyVisit> visits;
    private List<WeeklyRecharge> recharges;

    public TransactionSummary(String clientId, List<WeeklyVisit> visits, List<WeeklyRecharge> recharges) {
        this.clientId = clientId;
        this.visits = visits;
        this.recharges = recharges;
    }

    public String getClientId() {
        return clientId;
    }

    public List<WeeklyVisit> getVisits() {
        return visits;
    }

    public List<WeeklyRecharge> getRecharges() {
        return recharges;
    }
}