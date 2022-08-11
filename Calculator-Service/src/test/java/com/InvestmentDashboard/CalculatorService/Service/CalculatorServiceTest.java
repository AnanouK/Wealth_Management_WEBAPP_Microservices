package com.InvestmentDashboard.CalculatorService.Service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private CalculatorService undertest = new CalculatorService();

    @Test
    void resultList() {
        //given
        int times = 1;
        float initialcapital = 1000;
        float pourcentage = 12;
        float monthly = 50;
        //when
        String response = undertest.resultList(times,initialcapital,pourcentage,monthly);
        //then
        assertEquals(response, "[{\"Mois\":\"1\",\"allInvest\":\"1050\",\"allWon\":\"10.5\",\"monthEarn\":\"10.5\",\"Total\":1060.5},{\"Mois\":\"2\",\"allInvest\":\"1100\",\"allWon\":\"21.60498\",\"monthEarn\":\"11.10498\",\"Total\":1121.605},{\"Mois\":\"3\",\"allInvest\":\"1150\",\"allWon\":\"33.321045\",\"monthEarn\":\"11.716064\",\"Total\":1183.321},{\"Mois\":\"4\",\"allInvest\":\"1200\",\"allWon\":\"45.654297\",\"monthEarn\":\"12.333252\",\"Total\":1245.6543},{\"Mois\":\"5\",\"allInvest\":\"1250\",\"allWon\":\"58.61084\",\"monthEarn\":\"12.956543\",\"Total\":1308.6108},{\"Mois\":\"6\",\"allInvest\":\"1300\",\"allWon\":\"72.1969\",\"monthEarn\":\"13.58606\",\"Total\":1372.1969},{\"Mois\":\"7\",\"allInvest\":\"1350\",\"allWon\":\"86.41882\",\"monthEarn\":\"14.221924\",\"Total\":1436.4188},{\"Mois\":\"8\",\"allInvest\":\"1400\",\"allWon\":\"101.28296\",\"monthEarn\":\"14.864136\",\"Total\":1501.283},{\"Mois\":\"9\",\"allInvest\":\"1450\",\"allWon\":\"116.79578\",\"monthEarn\":\"15.512817\",\"Total\":1566.7958},{\"Mois\":\"10\",\"allInvest\":\"1500\",\"allWon\":\"132.96375\",\"monthEarn\":\"16.167969\",\"Total\":1632.9637},{\"Mois\":\"11\",\"allInvest\":\"1550\",\"allWon\":\"149.79333\",\"monthEarn\":\"16.82959\",\"Total\":1699.7933},{\"Mois\":\"12\",\"allInvest\":\"1600\",\"allWon\":\"167.29126\",\"monthEarn\":\"17.497925\",\"Total\":1767.2913}]");


    }
}