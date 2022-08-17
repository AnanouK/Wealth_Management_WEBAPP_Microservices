package com.venenium.StatisticsService.Service;
import com.venenium.StatisticsService.Model.Statistics;
import com.venenium.StatisticsService.Repository.StatisticsRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private final StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public String getDataForOne(String investmentName, String clientUsername) {

        List<Statistics> allStatistics = statisticsRepository.findByNameAndUsernameOrderByIdAsc(investmentName, clientUsername);
        String response = "[";
        float lastActual = 0;

        for (int j = 0; j <= allStatistics.size()-1 ; j++) {
            if (j == allStatistics.size()-1){

                response += "{\"Id\":";
                response += "\"" + allStatistics.get(j).getId() + "\"" + ",";
                response += "\"Date\":";
                response += "\"" + allStatistics.get(j).getStart() + "\"" + ",";
                response += "\"Capital\":";
                response += String.valueOf(allStatistics.get(j).getActual()) + ",";
                response += "\"Pourcentage\":";
                response +=  + (allStatistics.get(j).getActual() - lastActual)/allStatistics.get(j).getActual() * 100 + "}";
                lastActual = allStatistics.get(j).getActual();
            }

            else {
                if (j == 0)
                {
                    lastActual = allStatistics.get(j).getActual();
                }
                response += "{\"Id\":";
                response += "\"" + allStatistics.get(j).getId() + "\"" + ",";
                response += "\"Date\":";
                response += "\"" + allStatistics.get(j).getStart() + "\"" + ",";
                response += "\"Capital\":";
                response += String.valueOf(allStatistics.get(j).getActual()) + ",";
                response += "\"Pourcentage\":";
                response +=  + (allStatistics.get(j).getActual() - lastActual)/allStatistics.get(j).getActual() * 100 + "},";
                lastActual = allStatistics.get(j).getActual();
            }

        }
        response +="]";

        return response;
    }

    public ResponseEntity<String> addStatistic(Statistics statistics) {

         List<Statistics> allStatistics = statisticsRepository.findByNameAndUsernameOrderByIdAsc(statistics.getName(),statistics.getUsername());

         //If the row already exist with the same date, we will change it and replace it instead of create a new one
         if (allStatistics.size()!=0)
         {
             if (allStatistics.get(allStatistics.size()-1).getStart().equals(statistics.getStart()))
             {
                 Statistics lastStat = allStatistics.get(allStatistics.size()-1);
                 lastStat.setId(allStatistics.get(allStatistics.size()-1).getId());
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

    public List<Statistics> allStatistics() {return statisticsRepository.findAll();}

    @Transactional
    public void delete(String name, String username) {statisticsRepository.deleteByNameAndUsername(name,username);}

    @Transactional
    public void deleteStatistic(int id) {
        statisticsRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll(String username) { statisticsRepository.deleteByUsername(username);
    }

    public List<Statistics> addDataForEmptyDatesForGlobalChart(String username, String name) {

        List<Statistics> allStatistics = statisticsRepository.findByNameAndUsernameOrderByIdAsc(name, username);
        List<Statistics> response = new ArrayList<>();

        if (allStatistics.size() > 0)
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
            LocalDateTime now = LocalDateTime.now();
            int numberOfTheDayOfTheMonth = now.getDayOfMonth();

            Statistics last = allStatistics.get(allStatistics.size() - 1);
            String[] parsing = last.getStart().split("-");

            if (Integer.parseInt(parsing[0]) != numberOfTheDayOfTheMonth && now.getMonthValue() == Integer.parseInt(parsing[1]))
            {
                for (int i = 0; i < numberOfTheDayOfTheMonth - Integer.parseInt(parsing[0]); i++)
                {
                    Statistics newOne = new Statistics(last.getName(), (numberOfTheDayOfTheMonth - (numberOfTheDayOfTheMonth - Integer.parseInt(parsing[0])) + i + 1) + "-" + now.getMonthValue() + "-" + now.getYear(), last.getCapital(), last.getActual(), last.getApi(), last.getApikey(), last.getSecret(), last.getUsername());
                    addStatistic(newOne);
                    response.add(newOne);
                }
            }
        }
        return response;
    }
}
