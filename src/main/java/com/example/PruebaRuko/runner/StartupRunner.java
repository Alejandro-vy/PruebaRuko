package com.example.PruebaRuko.runner;

import com.example.PruebaRuko.service.BenefitDetectionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class StartupRunner implements CommandLineRunner {

    private final BenefitDetectionService benefitDetectionService;

    public StartupRunner(BenefitDetectionService benefitDetectionService) {
        this.benefitDetectionService = benefitDetectionService;
    }

    @Override
    public void run(String... args) throws Exception {
        benefitDetectionService.processEvents();
    }
}
