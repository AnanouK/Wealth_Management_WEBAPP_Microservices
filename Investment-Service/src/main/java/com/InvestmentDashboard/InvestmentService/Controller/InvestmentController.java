package com.InvestmentDashboard.InvestmentService.Controller;

import com.InvestmentDashboard.InvestmentService.Model.Investment;
import com.InvestmentDashboard.InvestmentService.Service.InvestmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/investments")
@ResponseStatus(HttpStatus.OK)
@AllArgsConstructor

public class InvestmentController {

    private final InvestmentService investmentService;


    @CrossOrigin(origins = "*")
    @GetMapping("/allinvestments")
    @ResponseStatus(HttpStatus.OK)
    public List<Investment> allinvestments ()
    {
        return investmentService.allinvestments();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Investment> getinvestmentbyid(@PathVariable   int id)
    {
        return investmentService.getinvestmentbyid(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addinvestment")
    @ResponseStatus(HttpStatus.OK)
    public Investment addinvestment (@RequestBody Investment investment)
    {
        return investmentService.addinvestment(investment);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allcapital")
    @ResponseStatus(HttpStatus.OK)
    public float allcapital()
    {
       return investmentService.allcapital();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allactual")
    @ResponseStatus(HttpStatus.OK)
    public float allactual()
    {
        return investmentService.allactual();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allbenefice")
    @ResponseStatus(HttpStatus.OK)
    public float allbenefice()
    {
        return investmentService.allbenefice();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/benefice")
    @ResponseStatus(HttpStatus.OK)
    public float getbenefice (@PathVariable int id)
    {
        return investmentService.getbenefice(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/pourcentagebenefice")
    @ResponseStatus(code = HttpStatus.OK)
    public float pourcentagebeneficeallinvestments ()
    {
        return investmentService.pourcentagebeneficeallinvestments();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/alldata")
    @ResponseStatus(HttpStatus.OK)
    public String alldata()
    {
        return investmentService.alldata();
    }

    @CrossOrigin(origins = "*")
    @PutMapping ("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Investment updateinvestment(@PathVariable int id, @RequestBody Investment investment)
    {
        return investmentService.updateinvestment(id, investment);
    }

    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public String ok() {
        return "Class Level HTTP Status Overriden. The HTTP Status will be OK (CODE 200)\n";
    }











}


