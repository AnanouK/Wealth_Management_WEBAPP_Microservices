package com.InvestmentDashboard.InvestmentService.Repository;

import com.InvestmentDashboard.InvestmentService.Model.Investment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InvestmentRepositoryTest {

    @Autowired
    private InvestmentRepository underTest;

    @Test
    void itShouldFindAllInvestmentsByUsername() {
        //Given
        String username = "jack";
        Investment investment = new Investment(
                "name","2022-6-13",1000,1500,500,false,"","",username
        );
        underTest.save(investment);

        //When
        List<Investment> expected = underTest.findAllByUsername(username);

        //Then
        assertThat(expected).isNotNull();
    }
}