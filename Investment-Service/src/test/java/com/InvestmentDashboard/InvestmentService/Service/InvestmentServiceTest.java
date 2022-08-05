package com.InvestmentDashboard.InvestmentService.Service;

import com.InvestmentDashboard.InvestmentService.Model.Investment;
import com.InvestmentDashboard.InvestmentService.Repository.InvestmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DataJpaTest

class InvestmentServiceTest {

    @Mock
    private InvestmentRepository investmentRepository;
    private InvestmentService underTest;

    @BeforeEach
    void setUp() throws Exception
    {
        underTest = new InvestmentService(investmentRepository);
    }

    /////////////////////////////////////////////////   TEST  ////////////////////////////////////////

    @Test
    void shouldGetAllInvestments() {
        //when
        underTest.allinvestments();
        //then
        verify(investmentRepository).findAll();
    }

    @Test
    void shouldAddInvestment() {
        //given
        Investment investment = new Investment(
                "name","2022-6-13",1000,1500,0,false,"","","jack"
        );
        //when
        underTest.addinvestment(investment);

        //then
        ArgumentCaptor<Investment> investmentArgumentCaptor = ArgumentCaptor.forClass(Investment.class);
        verify(investmentRepository).save(investmentArgumentCaptor.capture());

        Investment captured = investmentArgumentCaptor.getValue();
        assertThat(captured.getBenefice()).isEqualTo(captured.getActual()-captured.getCapital());

    }

    @Test
    // Error because the name of this investment already exist for this username
    void shoulErrorWhenAddInvestment() {
        //given
        Investment investment = new Investment(
                "name","2022-6-13",1000,1500,0,false,"","","jack"
        );
        List<Investment> listeofinvestments = new ArrayList<Investment>();
        listeofinvestments.add(investment);
        given(investmentRepository.findAllByNameAndUsername(investment.getName(),investment.getUsername())).willReturn(listeofinvestments);
        //when
        underTest.addinvestment(investment);

        //then
        verify(investmentRepository, never()).save(any());

    }

    @Test
    void shouldGetInvestmentById() {
        //given
        Investment investment = new Investment(1,
                "name","2022-6-13",1000,1500,0,false,"","","jack"
        );

        //when
        underTest.getinvestmentbyid(investment.getId());
        //then
        verify(investmentRepository).findById(investment.getId());
    }

    @Test
    void shouldGiveAllCapital() {
        //given
        Investment investment1 = new Investment(
                "name1","2022-6-13",1000,1500,0,false,"","","jack"
        );
        Investment investment2 = new Investment(
                "name2","2022-6-13",1000,1500,0,false,"","","jack"
        );
        List<Investment> listeofinvestments = new ArrayList<Investment>();
        listeofinvestments.add(investment1);
        listeofinvestments.add(investment2);

        given(investmentRepository.findAll()).willReturn(listeofinvestments);
        //when
        float response = underTest.allcapital();
        //then
        assertEquals(response,investment1.getCapital()+investment2.getCapital());
    }

    @Test
    void allactual() {
        //given
        Investment investment1 = new Investment(
                "name1","2022-6-13",1000,1500,0,false,"","","jack"
        );
        Investment investment2 = new Investment(
                "name2","2022-6-13",1000,2000,0,false,"","","jack"
        );
        List<Investment> listeofinvestments = new ArrayList<Investment>();
        listeofinvestments.add(investment1);
        listeofinvestments.add(investment2);

        given(investmentRepository.findAll()).willReturn(listeofinvestments);
        //when
        float response = underTest.allactual();
        //then
        assertEquals(response,investment1.getActual()+investment2.getActual());
    }

    @Test
    void allbenefice() {
        //given
        Investment investment1 = new Investment(
                "name1","2022-6-13",1000,1500,500,false,"","","jack"
        );
        Investment investment2 = new Investment(
                "name2","2022-6-13",1000,2000,1000,false,"","","jack"
        );
        List<Investment> listeofinvestments = new ArrayList<Investment>();
        listeofinvestments.add(investment1);
        listeofinvestments.add(investment2);

        given(investmentRepository.findAll()).willReturn(listeofinvestments);
        //when
        float response = underTest.allbenefice();
        //then
        assertEquals(response,investment1.getBenefice()+investment2.getBenefice());
    }

    @Test
    void getbenefice() {
        //given
        Investment investment = new Investment(1,
                "name","2022-6-13",1000,1500,500,false,"","","jack"
        );
        given(investmentRepository.findById(investment.getId())).willReturn(Optional.of(investment));

        //when
        Float response = underTest.getbenefice(investment.getId());
        //then
        verify(investmentRepository).findById(investment.getId());
        assertEquals(response,500);
    }

    @Test
    void pourcentagebeneficeallinvestments() {
        //given
        Investment investment1 = new Investment(
                "name1","2022-6-13",1000,1500,500,false,"","","jack"
        );
        Investment investment2 = new Investment(
                "name2","2022-6-13",1000,2000,1000,false,"","","jack"
        );
        List<Investment> listeofinvestments = new ArrayList<Investment>();
        listeofinvestments.add(investment1);
        listeofinvestments.add(investment2);

        given(investmentRepository.findAll()).willReturn(listeofinvestments);
        //when
        float response = underTest.pourcentagebeneficeallinvestments();
        //then
        assertEquals(response,(investment1.getBenefice()+investment2.getBenefice())/(investment1.getCapital()+investment2.getCapital()) * 100);

    }

    @Test
    void shouldGetAllData() {
        //given
        String username = "jack";

        Investment investment1 = new Investment(
                "name1","2022-6-13",1000,1500,500,false,"","",username
        );
        Investment investment2 = new Investment(
                "name2","2022-6-13",1000,2000,1000,false,"","",username
        );
        List<Investment> listeofinvestments = new ArrayList<Investment>();
        listeofinvestments.add(investment1);
        listeofinvestments.add(investment2);

        given(investmentRepository.findAllByUsername(username)).willReturn(listeofinvestments);
        //when
        String response = underTest.alldata(username);
        //then
        assertEquals(response,"{ \"base\":2000.0, \"actual\":3500.0, \"benefice\":1500.0, \"pourcentageallbenefice\":75.0}");
    }

    @Test
    void updateinvestment() {
        //given
        Investment investment1 = new Investment(
                "name1","2022-6-13",1000,1500,500,false,"","","jack"
        );
        Investment investment2 = new Investment(
                "name1","2022-6-14",1200,2000,800,false,"","","jack"
        );

        given(investmentRepository.findById(anyInt())).willReturn(Optional.of(investment1));

        //when
        underTest.updateinvestment(investment1.getId(), investment2);
        //then
        ArgumentCaptor<Investment> investmentArgumentCaptor = ArgumentCaptor.forClass(Investment.class);
        verify(investmentRepository).save(investmentArgumentCaptor.capture());

        Investment captured = investmentArgumentCaptor.getValue();
        assertEquals(captured.toString(),investment2.toString());
    }

    @Test
    void deleteInvestment() {
        //given
        Investment investment1 = new Investment(1,
                "name1","2022-6-13",1000,1500,500,false,"","","jack"
        );
        //when
        underTest.deleteInvestment(investment1.getId());
        //then
        verify(investmentRepository).deleteById(investment1.getId());
    }

    @Test
    void allinvestmentsof() {
        //given
        String username = "jack";
        //when
        underTest.allinvestmentsof(username);
        //then
        verify(investmentRepository).findAllByUsername(username);
    }

    @Test
    void deleteAll()
    {
        //given
        String username = "jack";
        //when
        underTest.deleteAll(username);
        //then
        verify(investmentRepository).deleteByUsername(username);
    }
}