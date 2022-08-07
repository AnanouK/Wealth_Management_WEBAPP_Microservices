package com.venenium.StatisticsService.Service;

import com.venenium.StatisticsService.Model.Statistics;
import com.venenium.StatisticsService.Repository.StatisticsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class StatisticsServiceTest {

    @Mock
    private StatisticsRepository statisticsRepository;
    private StatisticsService underTest;

    @BeforeEach
    void setUp() throws Exception
    {
        underTest = new StatisticsService(statisticsRepository);
    }


    @Test
    void getDataForOne() {
        //given
        String name = "test";
        String username = "jack";
        Statistics statistics1 = new Statistics(1,
                name,"2022-6-13", "1000.0F", 1500.0F,false,"","",username
        );
        Statistics statistics2 = new Statistics(2,
                name,"2022-6-14", "1000.0F", 1600.0F,false,"","",username
        );
        List<Statistics> listeofinvestments = new ArrayList<Statistics>();
        listeofinvestments.add(statistics1);
        listeofinvestments.add(statistics2);

        given(statisticsRepository.findByNameAndUsername(name,username)).willReturn(listeofinvestments);
        //when
        String response = underTest.getDataForOne(name,username);
        //then
        verify(statisticsRepository).findByNameAndUsername(name,username);
        assertEquals(response, "[{\"Id\":"+"\""+statistics2.getId()+"\","+"\"Date\":"+"\""+statistics2.getStart()+"\","+"\"Capital\":"+statistics2.getActual()+"},"+ "{\"Id\":"+"\""+ statistics1.getId()+"\","+"\"Date\":"+"\""+statistics1.getStart()+"\","+"\"Capital\":"+statistics1.getActual()+"}"+ "]");

    }

    @Test
    void addStatisticWithRowAlreadyInTheDatabase() {
        //given
        String name = "test";
        String username = "jack";
        Statistics alreadysaved = new Statistics(1,
                name,"2022-6-13", "1000.0F", 1500.0F,false,"","",username
        );
        Statistics newone = new Statistics(2,
                name,"2022-6-13", "1200.0F", 1600.0F,false,"","",username
        );
        List<Statistics> listeofinvestments = new ArrayList<Statistics>();
        listeofinvestments.add(alreadysaved);

        given(statisticsRepository.findByNameAndUsername(name,username)).willReturn(listeofinvestments);
        //when
        underTest.addStatistic(newone);
        //then

        ArgumentCaptor<Statistics> investmentArgumentCaptor = ArgumentCaptor.forClass(Statistics.class);
        verify(statisticsRepository).save(investmentArgumentCaptor.capture());

        Statistics captured = investmentArgumentCaptor.getValue();
        assertEquals(captured.getId(), alreadysaved.getId());
        assertEquals(captured.getCapital(), alreadysaved.getCapital());
        assertEquals(captured.getActual(), alreadysaved.getActual());
    }

    @Test
    void addStatisticWithNoDataInDatabase() {
        //given
        String name = "test";
        String username = "jack";
        Statistics newone = new Statistics(
                name,"2022-6-13", "1200.0F", 1600.0F,false,"","",username
        );
        List<Statistics> listeofinvestments = new ArrayList<Statistics>();

        given(statisticsRepository.findByNameAndUsername(name,username)).willReturn(listeofinvestments);
        //when
        underTest.addStatistic(newone);
        //then

        ArgumentCaptor<Statistics> investmentArgumentCaptor = ArgumentCaptor.forClass(Statistics.class);
        verify(statisticsRepository).save(investmentArgumentCaptor.capture());

        Statistics captured = investmentArgumentCaptor.getValue();
        assertEquals(captured.toString(), newone.toString());

    }

    @Test
    void addStatisticWithDataInDatabaseButDifferentDate() {
        //given
        String name = "test";
        String username = "jack";
        Statistics alreadysaved = new Statistics(
                name,"2022-6-12", "1000.0F", 1500.0F,false,"","",username
        );
        Statistics newone = new Statistics(
                name,"2022-6-13", "1200.0F", 1600.0F,false,"","",username
        );
        List<Statistics> listeofinvestments = new ArrayList<Statistics>();
        listeofinvestments.add(alreadysaved);

        given(statisticsRepository.findByNameAndUsername(name,username)).willReturn(listeofinvestments);
        //when
        underTest.addStatistic(newone);
        //then

        ArgumentCaptor<Statistics> investmentArgumentCaptor = ArgumentCaptor.forClass(Statistics.class);
        verify(statisticsRepository).save(investmentArgumentCaptor.capture());

        Statistics captured = investmentArgumentCaptor.getValue();
        assertEquals(captured.toString(), newone.toString());
    }

    @Test
    void allStatistics() {
        //when
        underTest.allStatistics();
        //then
        verify(statisticsRepository).findAll();
    }

    @Test
    void delete() {
        //when
        underTest.delete(anyString(),anyString());
        //then
        verify(statisticsRepository).deleteByNameAndUsername(anyString(),anyString());
    }


    @Test
    void deleteStatistic() {
        //given
        int id = 127;
        //when
        underTest.deleteStatistic(id);
        //then
        verify(statisticsRepository).deleteById(id);

    }

    @Test
    void deleteAll() {
        //given
        String username = "jack";
        //when
        underTest.deleteAll(username);
        //then
        verify(statisticsRepository).deleteByUsername(username);

    }
}