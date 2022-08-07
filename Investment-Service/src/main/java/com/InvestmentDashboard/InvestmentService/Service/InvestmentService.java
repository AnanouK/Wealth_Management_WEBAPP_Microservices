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

    // Return All Investments
    public List<Investment> allinvestments()
    {
        return investmentRepository.findAll();
    }

    // Create One Investment, the benefice is calculate automatically
    public ResponseEntity<String> addinvestment(Investment investment) {

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

    // Return One Investment with the ID given
    public Optional<Investment> getinvestmentbyid(int id)
    {
        return investmentRepository.findById(id);
    }

    // Return The capital of all investments
    public float allcapital()
    {
        float result = 0;
        List<Investment> liste =  investmentRepository.findAll();
        if (!liste.isEmpty()) {
            for (int j = 0; j < liste.size(); j++) {
                result += liste.get(j).getCapital();
            }
            return result;
        }
        return result;
    }

    // Return The actual money of all investments
    public float allactual(String username)
    {
        float result = 0;
        List<Investment> liste =  investmentRepository.findAllByUsername(username);
        for (int j = 0; j < liste.size() ; j++) {
            result += liste.get(j).getActual();
        }
        return result;
    }

    // Return The benefice of all investments
    public float allbenefice()
    {
        float result = 0;
        List<Investment> liste =  investmentRepository.findAll();
        for (int j = 0; j < liste.size() ; j++) {
            result += liste.get(j).getBenefice();
        }
        return result;
    }

    // Return the benefice of one investment
    public float getbenefice(int id)
    {
       Optional<Investment> investment = investmentRepository.findById(id);
        return (investment.get().getActual() - investment.get().getCapital());
    }


    public float pourcentagebeneficeallinvestments() {
        return allbenefice() / allcapital() * 100;
    }

    public String alldata(String username) {
        float capital = 0;
        float actual = 0;
        float benefice = 0;

        List<Investment> liste =  investmentRepository.findAllByUsername(username);
        for (int j = 0; j < liste.size() ; j++) {
            capital += liste.get(j).getCapital();
            actual += liste.get(j).getActual();
            benefice += liste.get(j).getBenefice();
        }

        float pourcentageallbenefice = ((actual - capital) / capital) * 100;

        return"{ \"base\":" + capital +", \"actual\":" + actual+", \"benefice\":"+benefice+", \"pourcentageallbenefice\":"+pourcentageallbenefice+ "}";

    }

    public Investment updateinvestment(int id, Investment investmentDetails) {
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

    public List<Investment> allinvestmentsof(String username) {
        return investmentRepository.findAllByUsername(username);
    }
    @Transactional
    public void deleteAll(String username) {
        investmentRepository.deleteByUsername(username);
    }
}
