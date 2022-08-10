package com.InvestmentDashboard.CalculatorService.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@ResponseStatus(HttpStatus.OK)
@AllArgsConstructor
public class CalculatorController2
{
    @CrossOrigin(origins = "*")
    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public String ok() {
        return "Class Level HTTP Status Overriden. The HTTP Status will be OK (CODE 200)\n";
    }
}
