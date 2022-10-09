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

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    void shouldGetDataForOne() {
        //given
        String name = "test";
        String username = "jack";
        Statistics statistics1 = new Statistics(32,
                name,"2022-6-13", "1000.0F", 1500.0F,false,"","",username
        );
        Statistics statistics2 = new Statistics(44,
                name,"2022-6-14", "1000.0F", 1600.0F,false,"","",username
        );

        List<Statistics> listeofinvestments = new ArrayList<Statistics>();
        listeofinvestments.add(statistics1);
        listeofinvestments.add(statistics2);

        given(statisticsRepository.findByNameAndUsernameOrderByIdAsc(name,username)).willReturn(listeofinvestments);
        //when
        String response = underTest.getDataForOne(name,username);
        //then
        verify(statisticsRepository).findByNameAndUsernameOrderByIdAsc(name,username);
        assertEquals("[{\"Id\":\"32\",\"Date\":\"2022-6-13\",\"Capital\":1500.0,\"Capital de base\":1000.0F,\"Pourcentage\":0.0},{\"Id\":\"44\",\"Date\":\"2022-6-14\",\"Capital\":1600.0,\"Capital de base\":1000.0F,\"Pourcentage\":6.666667}]", response);

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

        given(statisticsRepository.findByNameAndUsernameOrderByIdAsc(name,username)).willReturn(listeofinvestments);
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

        given(statisticsRepository.findByNameAndUsernameOrderByIdAsc(name,username)).willReturn(listeofinvestments);
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

        given(statisticsRepository.findByNameAndUsernameOrderByIdAsc(name,username)).willReturn(listeofinvestments);
        //when
        underTest.addStatistic(newone);
        //then

        ArgumentCaptor<Statistics> investmentArgumentCaptor = ArgumentCaptor.forClass(Statistics.class);
        verify(statisticsRepository).save(investmentArgumentCaptor.capture());

        Statistics captured = investmentArgumentCaptor.getValue();
        assertEquals(captured.toString(), newone.toString());
    }

    @Test
    void shouldGetAllStatistics() {
        //when
        underTest.allStatistics();
        //then
        verify(statisticsRepository).findAll();
    }

    @Test
    void shouldDelete() {
        //when
        underTest.delete(anyString(),anyString());
        //then
        verify(statisticsRepository).deleteByNameAndUsername(anyString(),anyString());
    }


    @Test
    void shouldDeleteStatistic() {
        //given
        int id = 127;
        //when
        underTest.deleteStatistic(id);
        //then
        verify(statisticsRepository).deleteById(id);

    }

    @Test
    void shouldDeleteAll() {
        //given
        String username = "jack";
        //when
        underTest.deleteAll(username);
        //then
        verify(statisticsRepository).deleteByUsername(username);

    }

    @Test
    void shouldGetMonthlyPercentageWith01(){
        //given
        String name = "test";
        String username = "jack";
        Statistics statistics1 = new Statistics(
                name,"01-05-2022", "1300.0F", 1500.0F,false,"","",username
        );
        Statistics statistics2 = new Statistics(
                name,"01-06-2022", "1300.0F", 1600.0F,false,"","",username
        );
        Statistics statistics3 = new Statistics(
                name,"18-06-2022", "1300.0F", 1800.0F,false,"","",username
        );
        List<Statistics> listeofinvestments = new ArrayList<Statistics>();
        listeofinvestments.add(statistics1);
        listeofinvestments.add(statistics2);
        listeofinvestments.add(statistics3);

        given(statisticsRepository.findByNameAndUsernameOrderByIdAsc(name,username)).willReturn(listeofinvestments);

        //when
        float response = underTest.getMonthlyPercentage(username,name);
        //verify
        assertEquals(20, response );
    }

    @Test
    void shouldGetMonthlyPercentageWithout01(){
        //given
        String name = "test";
        String username = "jack";
        Statistics statistics1 = new Statistics(
                name,"12-05-2022", "1300.0F", 1500.0F,false,"","",username
        );
        Statistics statistics2 = new Statistics(
                name,"15-06-2022", "1300.0F", 1600.0F,false,"","",username
        );
        Statistics statistics3 = new Statistics(
                name,"19-06-2022", "1300.0F", 1800.0F,false,"","",username
        );
        List<Statistics> listeofinvestments = new ArrayList<Statistics>();
        listeofinvestments.add(statistics1);
        listeofinvestments.add(statistics2);
        listeofinvestments.add(statistics3);

        given(statisticsRepository.findByNameAndUsernameOrderByIdAsc(name,username)).willReturn(listeofinvestments);

        //when
        float response = underTest.getMonthlyPercentage(username,name);
        //verify
        assertEquals(20, response );
    }

    @Test
    void shouldGetMonthlyPercentageWith2Rows(){
        //given
        String name = "test";
        String username = "jack";
        Statistics statistics1 = new Statistics(
                name,"01-06-2022", "1500.0F", 1550.0F,false,"","",username
        );
        Statistics statistics2 = new Statistics(
                name,"12-06-2022", "1500.0F", 1800.0F,false,"","",username
        );

        List<Statistics> listeofinvestments = new ArrayList<Statistics>();
        listeofinvestments.add(statistics1);
        listeofinvestments.add(statistics2);

        given(statisticsRepository.findByNameAndUsernameOrderByIdAsc(name,username)).willReturn(listeofinvestments);

        //when
        float response = underTest.getMonthlyPercentage(username,name);
        //verify
        assertEquals(20, response );
    }

    @Test
    void shouldGetMonthlyPercentageWith1Row(){
        //given
        String name = "test";
        String username = "jack";

        Statistics statistics1 = new Statistics(
                name,"12-06-2022", "1500.0F", 1800.0F,false,"","",username
        );

        List<Statistics> listeofinvestments = new ArrayList<Statistics>();
        listeofinvestments.add(statistics1);

        given(statisticsRepository.findByNameAndUsernameOrderByIdAsc(name,username)).willReturn(listeofinvestments);

        //when
        float response = underTest.getMonthlyPercentage(username,name);
        //verify
        assertEquals(20, response );
    }

    @Test
    void shouldGetMonthlyPercentageOf0(){
        //given
        String name = "test";
        String username = "jack";

        List<Statistics> listeofinvestments = new ArrayList<Statistics>();

        given(statisticsRepository.findByNameAndUsernameOrderByIdAsc(name,username)).willReturn(listeofinvestments);

        //when
        float response = underTest.getMonthlyPercentage(username,name);
        //verify
        assertEquals(0.0, response );
    }

}