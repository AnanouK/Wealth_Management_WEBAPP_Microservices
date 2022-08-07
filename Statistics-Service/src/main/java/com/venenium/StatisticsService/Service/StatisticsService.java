package com.venenium.StatisticsService.Service;
import com.venenium.StatisticsService.Model.Statistics;
import com.venenium.StatisticsService.Repository.StatisticsRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private final StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public String getDataForOne(String investmentName, String clientUsername) {

        List<Statistics> result = statisticsRepository.findByNameAndUsername(investmentName, clientUsername);
        String finalvar = "[";

        for (int j = result.size()-1; j >= 0 ; j--) {
            if (j == 0){
                finalvar += "{\"Id\":";
                finalvar += "\"" + result.get(j).getId() + "\"" + ",";
                finalvar += "\"Date\":";
                finalvar += "\"" + result.get(j).getStart() + "\"" + ",";
                finalvar += "\"Capital\":";
                finalvar += String.valueOf(result.get(j).getActual()) + "}";
            }

            else {
                finalvar += "{\"Id\":";
                finalvar += "\"" + result.get(j).getId() + "\"" + ",";
                finalvar += "\"Date\":";
                finalvar += "\"" + result.get(j).getStart() + "\"" + ",";
                finalvar += "\"Capital\":";
                finalvar += String.valueOf(result.get(j).getActual()) + "},";
            }

        }
        finalvar +="]";

        return finalvar;

    }

    public ResponseEntity<String> addStatistic(Statistics statistics) {

         List<Statistics> getData = statisticsRepository.findByNameAndUsername(statistics.getName(),statistics.getUsername());

         //If the row already exist with the same date, we will change it and replace it instead of create a new one
         if (getData.size()!=0)
         {
             if (getData.get(getData.size()-1).getStart().equals(statistics.getStart()))
             {
                 Statistics lastStat = getData.get(getData.size()-1);
                 lastStat.setId(getData.get(getData.size()-1).getId());
                 lastStat.setActual(statistics.getActual());
                 lastStat.setCapital(statistics.getCapital());
                 statisticsRepository.save(lastStat);
                 return new ResponseEntity<String>(HttpStatus.OK);
            }

             else
             {
                 Statistics newstats = new Statistics();
                 newstats.setName(statistics.getName());
                 newstats.setStart(statistics.getStart());
                 newstats.setActual(statistics.getActual());
                 newstats.setCapital(statistics.getCapital());
                 newstats.setApi(statistics.getApi());
                 newstats.setApikey(statistics.getApikey());
                 newstats.setSecret(statistics.getSecret());
                 newstats.setUsername(statistics.getUsername());
                 statisticsRepository.save(newstats);
                 return new ResponseEntity<String>(HttpStatus.CREATED);
             }
         }

         // else we create the new statistics
         else
         {
             Statistics newstats = new Statistics();
             newstats.setName(statistics.getName());
             newstats.setStart(statistics.getStart());
             newstats.setActual(statistics.getActual());
             newstats.setCapital(statistics.getCapital());
             newstats.setApi(statistics.getApi());
             newstats.setApikey(statistics.getApikey());
             newstats.setSecret(statistics.getSecret());
             newstats.setUsername(statistics.getUsername());
             statisticsRepository.save(newstats);
             return new ResponseEntity<String>(HttpStatus.CREATED);
         }
    }

    public List<Statistics> allStatistics() {

        return statisticsRepository.findAll();
    }

    @Transactional
    public void delete(String name, String username) {

        statisticsRepository.deleteByNameAndUsername(name,username);
    }

    @Transactional
    public void deleteStatistic(int id) {
        statisticsRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll(String username) { statisticsRepository.deleteByUsername(username);
    }
}
