package com.example.PruebaRuko.runner;

import com.example.PruebaRuko.service.BenefitDetectionService;
import com.example.PruebaRuko.service.TransactionSummaryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final BenefitDetectionService benefitDetectionService;
    private final TransactionSummaryService transactionSummaryService;

    public StartupRunner(BenefitDetectionService benefitDetectionService,
                         TransactionSummaryService transactionSummaryService) {
        this.benefitDetectionService = benefitDetectionService;
        this.transactionSummaryService = transactionSummaryService;
    }

    @Override
    public void run(String... args) throws Exception {
        benefitDetectionService.processEvents();
        transactionSummaryService.generateSummary();
    }
}

