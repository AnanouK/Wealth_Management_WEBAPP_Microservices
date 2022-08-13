package com.InvestmentDashboard.CalculatorService.Service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private CalculatorService undertest = new CalculatorService();

    @Test
    void resultList() {
        //given
        int times = 1;
        float initialcapital = 10000;
        float pourcentage = 12;
        float monthly = 50;
        float goal = 112;
        //when
        String response = undertest.resultList(times,initialcapital,pourcentage,monthly,goal);
        //then
        assertEquals(response, "[{\"Mois\":\"1\",\"allInvest\":\"10050\",\"allWon\":\"100.5\",\"monthEarn\":\"100.5\",\"Total\":10150.5},{\"Mois\":\"2\",\"allInvest\":\"10100\",\"allWon\":\"202.50488\",\"monthEarn\":\"102.00488\",\"Total\":10302.505},{\"Mois\":\"3\",\"allInvest\":\"10150\",\"allWon\":\"306.03027\",\"monthEarn\":\"103.52539\",\"Total\":10456.03},{\"Mois\":\"4\",\"allInvest\":\"10200\",\"allWon\":\"411.09082\",\"monthEarn\":\"105.06055\",\"Total\":10611.091},{\"Mois\":\"5\",\"allInvest\":\"10250\",\"allWon\":\"517.7012\",\"monthEarn\":\"106.61035\",\"Total\":10767.701},{\"Mois\":\"6\",\"allInvest\":\"10300\",\"allWon\":\"625.8779\",\"monthEarn\":\"108.17676\",\"Total\":10925.878},{\"Mois\":\"7\",\"allInvest\":\"10350\",\"allWon\":\"735.6367\",\"monthEarn\":\"109.75879\",\"Total\":11085.637},{\"Mois\":\"8\",\"allInvest\":\"10400\",\"allWon\":\"846.99316\",\"monthEarn\":\"111.356445\",\"Total\":11246.993},{\"Mois\":\"9\",\"allInvest\":\"10450\",\"allWon\":\"959.9629\",\"monthEarn\":\"112.96973\",\"Total\":11409.963},{\"Mois\":\"10\",\"allInvest\":\"10500\",\"allWon\":\"1074.5625\",\"monthEarn\":\"114.59961\",\"Total\":11574.5625},{\"Mois\":\"11\",\"allInvest\":\"10550\",\"allWon\":\"1190.8076\",\"monthEarn\":\"116.24512\",\"Total\":11740.808},{\"Mois\":\"12\",\"allInvest\":\"10600\",\"allWon\":\"1308.7158\",\"monthEarn\":\"117.9082\",\"Total\":11908.716},{\"Years\":\"0\"},{\"Months\":\"8\"}]");
    }

    @Test
    void resultListWhenGoalNotReached() {
        //given
        int times = 1;
        float initialcapital = 10000;
        float pourcentage = 12;
        float monthly = 50;
        float goal = 10000;
        //when
        String response = undertest.resultList(times,initialcapital,pourcentage,monthly,goal);
        //then
        assertEquals(response, "[{\"Mois\":\"1\",\"allInvest\":\"10050\",\"allWon\":\"100.5\",\"monthEarn\":\"100.5\",\"Total\":10150.5},{\"Mois\":\"2\",\"allInvest\":\"10100\",\"allWon\":\"202.50488\",\"monthEarn\":\"102.00488\",\"Total\":10302.505},{\"Mois\":\"3\",\"allInvest\":\"10150\",\"allWon\":\"306.03027\",\"monthEarn\":\"103.52539\",\"Total\":10456.03},{\"Mois\":\"4\",\"allInvest\":\"10200\",\"allWon\":\"411.09082\",\"monthEarn\":\"105.06055\",\"Total\":10611.091},{\"Mois\":\"5\",\"allInvest\":\"10250\",\"allWon\":\"517.7012\",\"monthEarn\":\"106.61035\",\"Total\":10767.701},{\"Mois\":\"6\",\"allInvest\":\"10300\",\"allWon\":\"625.8779\",\"monthEarn\":\"108.17676\",\"Total\":10925.878},{\"Mois\":\"7\",\"allInvest\":\"10350\",\"allWon\":\"735.6367\",\"monthEarn\":\"109.75879\",\"Total\":11085.637},{\"Mois\":\"8\",\"allInvest\":\"10400\",\"allWon\":\"846.99316\",\"monthEarn\":\"111.356445\",\"Total\":11246.993},{\"Mois\":\"9\",\"allInvest\":\"10450\",\"allWon\":\"959.9629\",\"monthEarn\":\"112.96973\",\"Total\":11409.963},{\"Mois\":\"10\",\"allInvest\":\"10500\",\"allWon\":\"1074.5625\",\"monthEarn\":\"114.59961\",\"Total\":11574.5625},{\"Mois\":\"11\",\"allInvest\":\"10550\",\"allWon\":\"1190.8076\",\"monthEarn\":\"116.24512\",\"Total\":11740.808},{\"Mois\":\"12\",\"allInvest\":\"10600\",\"allWon\":\"1308.7158\",\"monthEarn\":\"117.9082\",\"Total\":11908.716}]");
    }
}