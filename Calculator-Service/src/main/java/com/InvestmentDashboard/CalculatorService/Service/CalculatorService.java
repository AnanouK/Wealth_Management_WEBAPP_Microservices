package com.InvestmentDashboard.CalculatorService.Service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public String resultList (int times, float initialcapital, float pourcentage, float monthlymoney)
    {
        String finalvar = "[";
        pourcentage = pourcentage/100;


        float result  = initialcapital;
        for (int i = 0; i < times*12 ; i++) {
            result = (result+monthlymoney) * (1 + (pourcentage/12));

            if (i == times*12){
                finalvar += "{\"Mois\":";
                finalvar += "\"" + (i+1) + "\"" + ",";
                finalvar += "\"Total\":";
                finalvar += result + "}";
            }
            else {
                finalvar += "{\"Mois\":";
                finalvar += "\"" + (i+1) + "\"" + ",";
                finalvar += "\"Total\":";
                finalvar += result + "},";
            }
        }
        return finalvar;
    }
}
