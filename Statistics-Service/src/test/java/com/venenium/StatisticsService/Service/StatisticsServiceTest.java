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
        assertEquals(response, "[{\"Id\":\"32\",\"Date\":\"2022-6-13\",\"Capital\":1500.0,\"Pourcentage\":0.0},{\"Id\":\"44\",\"Date\":\"2022-6-14\",\"Capital\":1600.0,\"Pourcentage\":6.25}]");

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
    void shouldAddDataForEmptyDatesForGlobalChart() {
        //given
        String name = "global";
        String username = "jack";

        Statistics alreadysaved = new Statistics(
                name,"12-8-2022", "1000.0F", 1500.0F,false,"","",username
        );
        Statistics alreadysaved1 = new Statistics(
                name,"13-8-2022", "1200.0F", 1600.0F,false,"","",username
        );
        Statistics shouldAdd = new Statistics(
                name,"14-8-2022", "1200.0F", 1600.0F,false,"","",username
        );
        Statistics shouldAdd2 = new Statistics(
                name,"15-8-2022", "1200.0F", 1600.0F,false,"","",username
        );
        Statistics shouldAdd3 = new Statistics(
                name,"16-8-2022", "1200.0F", 1600.0F,false,"","",username
        );
        List<Statistics> listeOfInvestments = new ArrayList<Statistics>();
        listeOfInvestments.add(alreadysaved);
        listeOfInvestments.add(alreadysaved1);

        given(statisticsRepository.findByNameAndUsernameOrderByIdAsc(name,username)).willReturn(listeOfInvestments);
        //when
        List<Statistics> response = underTest.addDataForEmptyDatesForGlobalChart(username,name);
        //then
        assertEquals(response.get(0),shouldAdd);
        assertEquals(response.get(1),shouldAdd2);
        assertEquals(response.get(2),shouldAdd3);

    }
}