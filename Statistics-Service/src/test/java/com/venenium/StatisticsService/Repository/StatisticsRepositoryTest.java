package com.venenium.StatisticsService.Repository;

import com.venenium.StatisticsService.Model.Statistics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class StatisticsRepositoryTest {

    @Autowired
    private StatisticsRepository underTest;

    @Test
    void findByNameAndUsername() {
        //given
        Statistics statistic = new Statistics(1,
                "name","2022-6-13", 1000F,1500,false,"","","jack"
        );
        underTest.save(statistic);
        //when
        List<Statistics> response = underTest.findByNameAndUsername(statistic.getName(),statistic.getUsername());
        //then
        assertTrue(!response.isEmpty());
    }

    @Test
    void deleteByNameAndUsername() {
        //given
        Statistics statistic = new Statistics(1,
                "name","2022-6-13", 1000F,1500,false,"","","jack"
        );
        underTest.save(statistic);
        //when
        underTest.deleteByNameAndUsername(statistic.getName(),statistic.getUsername());
        //then
        assertTrue(underTest.findAll().isEmpty());
    }

    @Test
    void deleteByUsername() {
        //given
        Statistics statistic = new Statistics(1,
                "name","2022-6-13", 1000F,1500,false,"","","jack"
        );
        underTest.save(statistic);
        //when
        underTest.deleteByUsername(statistic.getUsername());
        //then
        assertTrue(underTest.findAll().isEmpty());
    }
}