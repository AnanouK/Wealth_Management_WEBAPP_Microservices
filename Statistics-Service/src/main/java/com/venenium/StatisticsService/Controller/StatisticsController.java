package com.venenium.StatisticsService.Controller;

import com.venenium.StatisticsService.Model.Statistics;
import com.venenium.StatisticsService.Service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@ResponseStatus(HttpStatus.OK)
@AllArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @CrossOrigin(origins = "*")
    @GetMapping("/getstatisticsof")
    public String getDataForOne(@RequestParam String investmentName,@RequestParam String clientUsername){

        return statisticsService.getDataForOne(investmentName,clientUsername);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/addstatistic")
    public ResponseEntity<String> addStatistic(@RequestBody Statistics statistics)
    {
        return statisticsService.addStatistic(statistics);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/allstatistics")
    public List<Statistics> allStatistics()
    {
        return statisticsService.allStatistics();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete")
    public void deleteAllWithName(@RequestParam String name, @RequestParam String username)
    {
        statisticsService.delete(name,username);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/all")
    public void deleteAll(@RequestParam String username)
    {
        statisticsService.deleteAll(username);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/onestat")
    public void deleteOne(@RequestParam int id)
    {
        statisticsService.deleteStatistic(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/checkempty")
    public List<Statistics> addDataForEmptyDates(@RequestParam String username, @RequestParam String name) {return statisticsService.addDataForEmptyDatesForGlobalChart(username,name);}



}
