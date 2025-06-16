package com.example.PruebaRuko.model;

import java.time.LocalDate;

public class WeeklyRecharge {
    private LocalDate weekStart;
    private double averageAmount;

    public WeeklyRecharge(LocalDate weekStart, double averageAmount) {
        this.weekStart = weekStart;
        this.averageAmount = averageAmount;
    }

    public LocalDate getWeekStart() {
        return weekStart;
    }

    public double getAverageAmount() {
        return averageAmount;
    }
}

