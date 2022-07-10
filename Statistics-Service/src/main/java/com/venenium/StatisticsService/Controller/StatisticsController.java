package com.venenium.StatisticsService.Controller;

import com.venenium.StatisticsService.Model.Statistics;
import com.venenium.StatisticsService.Service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@ResponseStatus(HttpStatus.OK)
@AllArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @CrossOrigin(origins = "*")
    @GetMapping("/getstatisticsof/{investmentName}/{clientUsername}")
    public String getDataForOne(@PathVariable String investmentName,@PathVariable String clientUsername){

        return statisticsService.getDataForOne(investmentName,clientUsername);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/addstatistic")
    public Statistics addStatistic(@RequestBody Statistics statistics)
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
    @DeleteMapping("/delete/{name}")
    public void delete(@PathVariable String name)
    {
        statisticsService.delete(name);
    }

}
