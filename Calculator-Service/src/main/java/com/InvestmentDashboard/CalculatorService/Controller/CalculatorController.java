package com.InvestmentDashboard.CalculatorService.Controller;

import com.InvestmentDashboard.CalculatorService.Service.CalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculator")
@AllArgsConstructor
public class CalculatorController {

    private CalculatorService calculatorService;

    @CrossOrigin(origins = "http://34.160.0.103/")
    @GetMapping("/")
    public String getData (@RequestParam int time, @RequestParam float initial, @RequestParam float pourcentage, @RequestParam float monthly ) {
        return calculatorService.resultList(time,initial,pourcentage,monthly);
    }
}
