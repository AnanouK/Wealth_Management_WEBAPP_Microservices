package com.InvestmentDashboard.InvestmentService.Service;

import com.InvestmentDashboard.InvestmentService.Model.Investment;
import com.InvestmentDashboard.InvestmentService.Repository.InvestmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InvestmentServiceTest {


    @Mock private InvestmentRepository investmentRepository;
    private InvestmentService underTest;

    @BeforeEach
    void setUp(){
        underTest = new InvestmentService(investmentRepository);
    }

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
    void getinvestmentbyid() {
    }

    @Test
    void allcapital() {
    }

    @Test
    void allactual() {
    }

    @Test
    void allbenefice() {
    }

    @Test
    void getbenefice() {
    }

    @Test
    void pourcentagebeneficeallinvestments() {
    }

    @Test
    void alldata() {
    }

    @Test
    void updateinvestment() {
    }

    @Test
    void deleteInvestment() {
    }

    @Test
    void allinvestmentsof() {
    }
}