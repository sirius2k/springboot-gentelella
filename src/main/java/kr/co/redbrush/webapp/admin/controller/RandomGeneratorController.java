package kr.co.redbrush.webapp.admin.controller;

import kr.co.redbrush.webapp.admin.domain.RandomValue;
import kr.co.redbrush.webapp.admin.service.RandomDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class RandomGeneratorController {
    @Autowired
    private RandomDataService randomDataService;

    @Value("${generator.startInclusive:0}")
    private int startInclusive;

    @Value("${generator.endInclusive:100}")
    private int endInclusive;

    @GetMapping(value = "/random/generate")
    public RandomValue generateRandom() {
        return randomDataService.generateRandom(startInclusive, endInclusive);
    }
}
