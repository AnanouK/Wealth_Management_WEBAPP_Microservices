package com.venenium.StatisticsService.Repository;

import com.venenium.StatisticsService.Model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository <Statistics, Integer> {

    List<Statistics> findByNameAndUsername(String name, String username);
}


