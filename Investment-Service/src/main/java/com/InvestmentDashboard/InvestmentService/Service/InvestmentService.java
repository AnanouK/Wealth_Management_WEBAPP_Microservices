package com.InvestmentDashboard.InvestmentService.Service;

import com.InvestmentDashboard.InvestmentService.Model.Investment;
import com.InvestmentDashboard.InvestmentService.Repository.InvestmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;

    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    public List<Investment> allInvestments()
    {
        return investmentRepository.findAll();
    }

    public ResponseEntity<String> addInvestment(Investment investment) {

         List<Investment> getName = investmentRepository.findAllByNameAndUsername(investment.getName(), investment.getUsername());

         if (!getName.isEmpty())
         {
            return new ResponseEntity<String>(HttpStatus.METHOD_NOT_ALLOWED);
         }

         float capital = investment.getCapital();
         float actual = investment.getActual();
         float benefice = actual - capital;

         Investment save = new Investment(investment.getName(), investment.getStart(), investment.getCapital(), investment.getActual(), benefice,false, " ", " ", investment.getUsername() );
         investmentRepository.save(save);

         return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    public Optional<Investment> getInvestmentById(int id)
    {
        return investmentRepository.findById(id);
    }

    public float allCapital(String username)
    {
        float allCapital = 0;

        List<Investment> liste =  investmentRepository.findAllByUsername(username);
        if (!liste.isEmpty()) {
            for (int j = 0; j < liste.size(); j++) {
                allCapital += liste.get(j).getCapital();
            }
        }

        return allCapital;
    }

    public float allActual(String username)
    {
        float allActual = 0;

        List<Investment> liste =  investmentRepository.findAllByUsername(username);
        for (int j = 0; j < liste.size() ; j++) {
            allActual += liste.get(j).getActual();
        }

        return allActual;
    }

    public float allBenefice(String username)
    {
        float allBenefice = 0;

        List<Investment> liste =  investmentRepository.findAllByUsername(username);
        for (int j = 0; j < liste.size() ; j++) {
            allBenefice += liste.get(j).getBenefice();
        }

        return allBenefice;
    }

    public float getBenefice(int id)
    {
       Optional<Investment> investment = investmentRepository.findById(id);
       return (investment.get().getActual() - investment.get().getCapital());
    }

    public String allData(String username) {
        float capital = 0;
        float actual = 0;
        float benefice = 0;

        List<Investment> liste =  investmentRepository.findAllByUsername(username);
        for (int j = 0; j < liste.size() ; j++) {
            capital += liste.get(j).getCapital();
            actual += liste.get(j).getActual();
            benefice += liste.get(j).getBenefice();
        }

        float pourcentageAllBenefice = ((actual - capital) / capital) * 100;

        return"{ \"base\":" + capital +", \"actual\":" + actual+", \"benefice\":"+benefice+", \"pourcentageallbenefice\":"+pourcentageAllBenefice+ "}";
    }

    public Investment updateInvestment(int id, Investment investmentDetails) {
         Optional<Investment> investmentRecup = investmentRepository.findById(id);
         Investment newInvestment = investmentRecup.get();
         newInvestment.setName(investmentDetails.getName());
         newInvestment.setStart(investmentDetails.getStart());
         newInvestment.setCapital(investmentDetails.getCapital());
         newInvestment.setActual(investmentDetails.getActual());
         newInvestment.setApi(investmentDetails.getApi());
         newInvestment.setApikey(investmentDetails.getApikey());
         newInvestment.setSecret(investmentDetails.getSecret());
         newInvestment.setBenefice(investmentDetails.getActual() - investmentDetails.getCapital());

        return investmentRepository.save(newInvestment);
    }

    public void deleteInvestment(int id) {
        investmentRepository.deleteById(id);
    }

    public List<Investment> allInvestmentsOf(String username) {
        return investmentRepository.findAllByUsername(username);
    }
    @Transactional
    public void deleteAll(String username) {
        investmentRepository.deleteByUsername(username);
    }
}
