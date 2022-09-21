package com.InvestmentDashboard.CalculatorService.Service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public String resultList (int times, float initialCapital, float pourcentage, float monthlyMoney, float goal, String pourcentageSelect)
    {
        String response = "[";
        float result  = initialCapital;
        pourcentage = pourcentage/100;
        float allInvest = initialCapital;
        float allWon = 0;
        float monthEarn = initialCapital;
        int goalAchieveMonth = 0;

        for (int i = 0; i < times*12 ; i++)
        {
            if (pourcentageSelect.equals("Annuel")) {
                result = (result + monthlyMoney) * (1 + (pourcentage / 12));
            }
            else if (pourcentageSelect.equals("Mensuel")){
                result = (result + monthlyMoney) * (1 + pourcentage);
            }
            allInvest += monthlyMoney;
            float lastAllWon = allWon;
            allWon = result-allInvest;
            monthEarn =  allWon - lastAllWon ;

            if (monthEarn >= goal && goalAchieveMonth == 0)
            {
                goalAchieveMonth = i;
            }

            if (i == (times*12)-1) {
                response += "{\"Mois\":";
                response += "\"" + (i + 1) + "\"" + ",";
                response += "\"allInvest\":";
                response += "\"" + (int) allInvest + "\"" + ",";
                response += "\"allWon\":";
                response += "\"" + (int) allWon + "\"" + ",";
                response += "\"monthEarn\":";
                response += "\"" + (int) monthEarn + "\"" + ",";
                response += "\"Total\":";
                response +=  (int) result + "}";
            }
            else
            {
                response += "{\"Mois\":";
                response += "\"" + (i + 1) + "\"" + ",";
                response += "\"allInvest\":";
                response += "\"" + (int) allInvest + "\"" + ",";
                response += "\"allWon\":";
                response += "\"" + (int) allWon + "\"" + ",";
                response += "\"monthEarn\":";
                response += "\"" + (int) monthEarn + "\"" + ",";
                response += "\"Total\":";
                response += (int) result + "},";
            }
        }

        if (goalAchieveMonth != 0) {
            response += ",{\"Years\":" + "\"" + (int)goalAchieveMonth/12 + "\"" + "}," + "{\"Months\":" + "\"" + (int)goalAchieveMonth%12 + "\"" + "}";
        }

        response += "]";

        return response;
    }
}
