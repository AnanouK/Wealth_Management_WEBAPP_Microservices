package com.InvestmentDashboard.InvestmentService.Controller;

import com.InvestmentDashboard.InvestmentService.Model.Investment;
import com.InvestmentDashboard.InvestmentService.Service.InvestmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/investments")
@AllArgsConstructor

public class InvestmentController {

    private final InvestmentService investmentService;


    @CrossOrigin(origins = "*")
    @GetMapping("/allinvestments")
    public List<Investment> allinvestments ()
    {
        return investmentService.allinvestments();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public Optional<Investment> getinvestmentbyid(@PathVariable   int id)
    {
        return investmentService.getinvestmentbyid(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addinvestment")
    public Investment addinvestment (@RequestBody Investment investment)
    {
        return investmentService.addinvestment(investment);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allcapital")
    public float allcapital()
    {
       return investmentService.allcapital();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allactual")
    public float allactual()
    {
        return investmentService.allactual();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allbenefice")
    public float allbenefice()
    {
        return investmentService.allbenefice();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/benefice")
    public float getbenefice (@PathVariable int id)
    {
        return investmentService.getbenefice(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/pourcentagebenefice")
    public float pourcentagebeneficeallinvestments ()
    {
        return investmentService.pourcentagebeneficeallinvestments();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/alldata")
    public String alldata()
    {
        return investmentService.alldata();
    }

    @CrossOrigin(origins = "*")
    @PutMapping ("/update/{id}")
    public Investment updateinvestment(@PathVariable int id, @RequestBody Investment investment)
    {
        return investmentService.updateinvestment(id, investment);
    }









}


