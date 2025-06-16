package com.example.PruebaRuko.service;

import com.example.PruebaRuko.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionSummaryService {

    public void generateSummary() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();

            InputStream is = getClass().getClassLoader().getResourceAsStream("ruklo_events_1000.json");
            List<Event> events = mapper.readValue(is, new TypeReference<>() {});

            Set<LocalDate> allWeeks = events.stream()
                    .map(e -> e.getTimestamp().toLocalDate().with(DayOfWeek.MONDAY))
                    .collect(Collectors.toCollection(TreeSet::new)); // ordenadas

            Map<String, List<Event>> groupedByClient = events.stream()
                    .collect(Collectors.groupingBy(Event::getClientId));

            List<TransactionSummary> summaries = new ArrayList<>();

            for (var entry : groupedByClient.entrySet()) {
                String clientId = entry.getKey();
                List<Event> clientEvents = entry.getValue();

                Map<LocalDate, List<Event>> visitsByWeek = clientEvents.stream()
                        .filter(e -> "visit".equals(e.getType()))
                        .collect(Collectors.groupingBy(e -> e.getTimestamp().toLocalDate().with(DayOfWeek.MONDAY)));

                List<WeeklyVisit> visitSummary = allWeeks.stream()
                        .map(week -> new WeeklyVisit(
                                week,
                                visitsByWeek.getOrDefault(week, List.of()).size()
                        ))
                        .collect(Collectors.toList());

                Map<LocalDate, List<Event>> rechargesByWeek = clientEvents.stream()
                        .filter(e -> "recharge".equals(e.getType()))
                        .collect(Collectors.groupingBy(e -> e.getTimestamp().toLocalDate().with(DayOfWeek.MONDAY)));

                List<WeeklyRecharge> rechargeSummary = allWeeks.stream()
                        .map(week -> {
                            List<Event> recharges = rechargesByWeek.getOrDefault(week, List.of());
                            double avg = recharges.isEmpty()
                                    ? 0
                                    : recharges.stream().mapToInt(Event::getAmount).average().orElse(0);
                            return new WeeklyRecharge(week, avg);
                        })
                        .collect(Collectors.toList());

                summaries.add(new TransactionSummary(clientId, visitSummary, rechargeSummary));
            }

            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            File outputDir = new File("outputs");
            if (!outputDir.exists()) outputDir.mkdirs();

            mapper.writeValue(new File(outputDir, "summary_output.json"), summaries);

            System.out.println("\n✅ Resumen exportado a summary_output.json");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
