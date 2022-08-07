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
    public List<Investment> allinvestmentsof (@RequestParam String username)
    {
        return investmentService.allinvestmentsof(username);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Investment> getinvestmentbyid(@RequestParam int id)
    {
        return investmentService.getinvestmentbyid(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addinvestment")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> addinvestment (@RequestBody Investment investment)
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
    public float allactual(@RequestParam String username)
    {
        return investmentService.allactual(username);
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
    public String alldata(@RequestParam String username)
    {
        return investmentService.alldata(username);
    }

    @CrossOrigin(origins = "*")
    @PutMapping ("/update")
    @ResponseStatus(HttpStatus.OK)
    public Investment updateinvestment(@RequestParam int id, @RequestBody Investment investment)
    {
        return investmentService.updateinvestment(id, investment);
    }

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


