package com.venenium.StatisticsService.Service;

import com.venenium.StatisticsService.Model.Statistics;
import com.venenium.StatisticsService.Repository.StatisticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;


    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public String getDataForOne(String investmentName, String clientUsername) {

        List<Statistics> result = statisticsRepository.findByNameAndUsername(investmentName, clientUsername);

        String finalvar = "";

        for (int j = 0; j < result.size() ; j++) {

            finalvar.concat("{name:");
            finalvar.concat(result.get(j).getDate()).concat(",");
            finalvar.concat("Capital");
            finalvar.concat(String.valueOf(result.get(j).getActual())).concat("},");
        }

        return finalvar;

    }

    public Statistics addStatistic(Statistics statistics) {
        return statisticsRepository.save(statistics);
    }
}
