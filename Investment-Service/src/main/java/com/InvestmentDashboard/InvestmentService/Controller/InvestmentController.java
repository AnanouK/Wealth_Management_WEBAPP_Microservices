package com.InvestmentDashboard.InvestmentService.Controller;

import com.InvestmentDashboard.InvestmentService.Model.Investment;
import com.InvestmentDashboard.InvestmentService.Service.InvestmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Investment> allInvestmentsOf (@RequestParam String username)
    {
        return investmentService.allInvestmentsOf(username);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Investment> getInvestmentById(@RequestParam int id)
    {
        return investmentService.getInvestmentById(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addinvestment")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> addInvestment (@RequestBody Investment investment)
    {
        return investmentService.addInvestment(investment);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allcapital")
    @ResponseStatus(HttpStatus.OK)
    public float allCapital(@RequestParam String username)
    {
       return investmentService.allCapital(username);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allactual")
    @ResponseStatus(HttpStatus.OK)
    public float allActual(@RequestParam String username)
    {
        return investmentService.allActual(username);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allbenefice")
    @ResponseStatus(HttpStatus.OK)
    public float allBenefice(@RequestParam String username) {return investmentService.allBenefice(username);}

    @CrossOrigin(origins = "*")
    @GetMapping("/benefice")
    @ResponseStatus(HttpStatus.OK)
    public float getBenefice (@PathVariable int id)
    {
        return investmentService.getBenefice(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/alldata")
    @ResponseStatus(HttpStatus.OK)
    public String allData(@RequestParam String username)
    {
        return investmentService.allData(username);
    }

    @CrossOrigin(origins = "*")
    @PutMapping ("/update")
    @ResponseStatus(HttpStatus.OK)
    public Investment updateInvestment(@RequestParam int id, @RequestBody Investment investment) {return investmentService.updateInvestment(id, investment);}

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete")
    public void deleteInvestment(@RequestParam int id){
        investmentService.deleteInvestment(id);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/all")
    public void deleteAll(@RequestParam String username){
        investmentService.deleteAll(username);
    }











}


