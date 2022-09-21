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
        String pourcentageSelectAnnuel = "Annuel";
        String pourcentageSelectMensuel = "Mensuel";
        //when
        String responseAnnuel = undertest.resultList(times,initialcapital,pourcentage,monthly,goal,pourcentageSelectAnnuel);
        String responseMensuel = undertest.resultList(times,initialcapital,pourcentage,monthly,goal,pourcentageSelectMensuel);
        //then
        assertEquals(responseAnnuel, "[{\"Mois\":\"1\",\"allInvest\":\"10050\",\"allWon\":\"100\",\"monthEarn\":\"100\",\"Total\":10150},{\"Mois\":\"2\",\"allInvest\":\"10100\",\"allWon\":\"202\",\"monthEarn\":\"102\",\"Total\":10302},{\"Mois\":\"3\",\"allInvest\":\"10150\",\"allWon\":\"306\",\"monthEarn\":\"103\",\"Total\":10456},{\"Mois\":\"4\",\"allInvest\":\"10200\",\"allWon\":\"411\",\"monthEarn\":\"105\",\"Total\":10611},{\"Mois\":\"5\",\"allInvest\":\"10250\",\"allWon\":\"517\",\"monthEarn\":\"106\",\"Total\":10767},{\"Mois\":\"6\",\"allInvest\":\"10300\",\"allWon\":\"625\",\"monthEarn\":\"108\",\"Total\":10925},{\"Mois\":\"7\",\"allInvest\":\"10350\",\"allWon\":\"735\",\"monthEarn\":\"109\",\"Total\":11085},{\"Mois\":\"8\",\"allInvest\":\"10400\",\"allWon\":\"846\",\"monthEarn\":\"111\",\"Total\":11246},{\"Mois\":\"9\",\"allInvest\":\"10450\",\"allWon\":\"959\",\"monthEarn\":\"112\",\"Total\":11409},{\"Mois\":\"10\",\"allInvest\":\"10500\",\"allWon\":\"1074\",\"monthEarn\":\"114\",\"Total\":11574},{\"Mois\":\"11\",\"allInvest\":\"10550\",\"allWon\":\"1190\",\"monthEarn\":\"116\",\"Total\":11740},{\"Mois\":\"12\",\"allInvest\":\"10600\",\"allWon\":\"1308\",\"monthEarn\":\"117\",\"Total\":11908},{\"Years\":\"0\"},{\"Months\":\"8\"}]");
        assertEquals(responseMensuel, "[{\"Mois\":\"1\",\"allInvest\":\"10050\",\"allWon\":\"1206\",\"monthEarn\":\"1206\",\"Total\":11256},{\"Mois\":\"2\",\"allInvest\":\"10100\",\"allWon\":\"2562\",\"monthEarn\":\"1356\",\"Total\":12662},{\"Mois\":\"3\",\"allInvest\":\"10150\",\"allWon\":\"4088\",\"monthEarn\":\"1525\",\"Total\":14238},{\"Mois\":\"4\",\"allInvest\":\"10200\",\"allWon\":\"5802\",\"monthEarn\":\"1714\",\"Total\":16002},{\"Mois\":\"5\",\"allInvest\":\"10250\",\"allWon\":\"7729\",\"monthEarn\":\"1926\",\"Total\":17979},{\"Mois\":\"6\",\"allInvest\":\"10300\",\"allWon\":\"9892\",\"monthEarn\":\"2163\",\"Total\":20192},{\"Mois\":\"7\",\"allInvest\":\"10350\",\"allWon\":\"12321\",\"monthEarn\":\"2429\",\"Total\":22671},{\"Mois\":\"8\",\"allInvest\":\"10400\",\"allWon\":\"15048\",\"monthEarn\":\"2726\",\"Total\":25448},{\"Mois\":\"9\",\"allInvest\":\"10450\",\"allWon\":\"18108\",\"monthEarn\":\"3059\",\"Total\":28558},{\"Mois\":\"10\",\"allInvest\":\"10500\",\"allWon\":\"21541\",\"monthEarn\":\"3432\",\"Total\":32041},{\"Mois\":\"11\",\"allInvest\":\"10550\",\"allWon\":\"25392\",\"monthEarn\":\"3850\",\"Total\":35942},{\"Mois\":\"12\",\"allInvest\":\"10600\",\"allWon\":\"29711\",\"monthEarn\":\"4319\",\"Total\":40311},{\"Years\":\"0\"},{\"Months\":\"1\"}]");
    }

    @Test
    void resultListWhenGoalNotReached() {
        //given
        int times = 1;
        float initialcapital = 10000;
        float pourcentage = 12;
        float monthly = 50;
        float goal = 10000;
        String pourcentageSelectAnnuel = "Annuel";
        //when
        String response = undertest.resultList(times,initialcapital,pourcentage,monthly,goal,pourcentageSelectAnnuel);
        //then
        assertEquals(response, "[{\"Mois\":\"1\",\"allInvest\":\"10050\",\"allWon\":\"100\",\"monthEarn\":\"100\",\"Total\":10150},{\"Mois\":\"2\",\"allInvest\":\"10100\",\"allWon\":\"202\",\"monthEarn\":\"102\",\"Total\":10302},{\"Mois\":\"3\",\"allInvest\":\"10150\",\"allWon\":\"306\",\"monthEarn\":\"103\",\"Total\":10456},{\"Mois\":\"4\",\"allInvest\":\"10200\",\"allWon\":\"411\",\"monthEarn\":\"105\",\"Total\":10611},{\"Mois\":\"5\",\"allInvest\":\"10250\",\"allWon\":\"517\",\"monthEarn\":\"106\",\"Total\":10767},{\"Mois\":\"6\",\"allInvest\":\"10300\",\"allWon\":\"625\",\"monthEarn\":\"108\",\"Total\":10925},{\"Mois\":\"7\",\"allInvest\":\"10350\",\"allWon\":\"735\",\"monthEarn\":\"109\",\"Total\":11085},{\"Mois\":\"8\",\"allInvest\":\"10400\",\"allWon\":\"846\",\"monthEarn\":\"111\",\"Total\":11246},{\"Mois\":\"9\",\"allInvest\":\"10450\",\"allWon\":\"959\",\"monthEarn\":\"112\",\"Total\":11409},{\"Mois\":\"10\",\"allInvest\":\"10500\",\"allWon\":\"1074\",\"monthEarn\":\"114\",\"Total\":11574},{\"Mois\":\"11\",\"allInvest\":\"10550\",\"allWon\":\"1190\",\"monthEarn\":\"116\",\"Total\":11740},{\"Mois\":\"12\",\"allInvest\":\"10600\",\"allWon\":\"1308\",\"monthEarn\":\"117\",\"Total\":11908}]");
    }
}