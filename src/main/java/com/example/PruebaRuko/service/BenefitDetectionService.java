package com.example.PruebaRuko.service;

import com.example.PruebaRuko.model.ClientVisitStreak;
import com.example.PruebaRuko.model.Event;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BenefitDetectionService {

    public void processEvents() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("ruklo_events_1000.json");
            List<Event> events = mapper.readValue(is, new TypeReference<>() {});

            events.sort(Comparator.comparing(Event::getTimestamp));

            Map<String, List<Event>> grouped = events.stream()
                    .collect(Collectors.groupingBy(e -> e.getClientId() + "::" + e.getStoreId()));

            List<ClientVisitStreak> streaks = new ArrayList<>();

            for (var entry : grouped.entrySet()) {
                List<Event> clientEvents = entry.getValue();
                List<Event> buffer = new ArrayList<>();

                for (Event e : clientEvents) {
                    if ("visit".equals(e.getType())) {
                        buffer.add(e);
                        if (buffer.size() == 5) {
                            LocalDateTime start = buffer.get(0).getTimestamp();
                            LocalDateTime end = buffer.get(4).getTimestamp();
                            streaks.add(new ClientVisitStreak(e.getClientId(), e.getStoreId(), start, end));
                            buffer.remove(0); // continue checking overlapping sequences
                        }
                    } else if ("recharge".equals(e.getType())) {
                        buffer.clear();
                    }
                }
            }

            streaks.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
