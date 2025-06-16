package com.example.PruebaRuko.model;

import java.time.LocalDate;

public class WeeklyVisit {
    private LocalDate weekStart;
    private long count;

    public WeeklyVisit(LocalDate weekStart, long count) {
        this.weekStart = weekStart;
        this.count = count;
    }

    public LocalDate getWeekStart() {
        return weekStart;
    }

    public long getCount() {
        return count;
    }
}
