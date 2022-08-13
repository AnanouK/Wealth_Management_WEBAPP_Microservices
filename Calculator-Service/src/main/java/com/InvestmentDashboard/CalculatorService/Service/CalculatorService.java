package com.InvestmentDashboard.CalculatorService.Service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public String resultList (int times, float initialcapital, float pourcentage, float monthlymoney, float goal)
    {
        String finalvar = "[";
        float result  = initialcapital;
        pourcentage = pourcentage/100;
        float allInvest = initialcapital;
        float allWon = 0;
        float monthEarn = initialcapital;
        int goalAchieveMonth = 0;

        for (int i = 0; i < times*12 ; i++) {
            result = (result+monthlymoney) * (1 + (pourcentage/12));
            allInvest += monthlymoney;
            float lastallwon = allWon;
            allWon = result-allInvest;
            monthEarn =  allWon - lastallwon ;

            if (monthEarn >= goal && goalAchieveMonth == 0)
            {
                goalAchieveMonth = i;
            }

            if (i == (times*12)-1) {
                finalvar += "{\"Mois\":";
                finalvar += "\"" + (i + 1) + "\"" + ",";
                finalvar += "\"allInvest\":";
                finalvar += "\"" + (int) allInvest + "\"" + ",";
                finalvar += "\"allWon\":";
                finalvar += "\"" + allWon + "\"" + ",";
                finalvar += "\"monthEarn\":";
                finalvar += "\"" + monthEarn + "\"" + ",";
                finalvar += "\"Total\":";
                finalvar += result + "}";
            }
            else
            {
                finalvar += "{\"Mois\":";
                finalvar += "\"" + (i + 1) + "\"" + ",";
                finalvar += "\"allInvest\":";
                finalvar += "\"" + (int) allInvest + "\"" + ",";
                finalvar += "\"allWon\":";
                finalvar += "\"" + allWon + "\"" + ",";
                finalvar += "\"monthEarn\":";
                finalvar += "\"" + monthEarn + "\"" + ",";
                finalvar += "\"Total\":";
                finalvar += result + "},";
            }
        }

        if (goalAchieveMonth != 0) {
            finalvar += ",{\"Years\":" + "\"" + (int)goalAchieveMonth/12 + "\"" + "}," + "{\"Months\":" + "\"" + (int)goalAchieveMonth%12 + "\"" + "}";
        }

        finalvar += "]";
        return finalvar;
    }
}
