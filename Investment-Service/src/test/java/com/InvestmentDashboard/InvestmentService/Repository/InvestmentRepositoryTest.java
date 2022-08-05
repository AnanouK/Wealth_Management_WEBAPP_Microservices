package com.InvestmentDashboard.InvestmentService.Repository;

import com.InvestmentDashboard.InvestmentService.Model.Investment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")

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
        Investment investment2 = new Investment(
                "name2","2022-6-13",2000,1500,500,false,"","",username
        );
        underTest.save(investment);
        underTest.save(investment2);

        //When
        List<Investment> expected = underTest.findAllByUsername(username);

        //Then
        assertThat(expected).isNotNull();
        System.out.println(expected);
    }

    @Test
    void itShouldFindAllInvestmentsByNameAndUsername() {
        //Given
        String username = "jack";
        String name = "new test";
        Investment investment = new Investment(
                name,"2022-6-13",1000,1500,500,false,"","",username
        );
        underTest.save(investment);

        //When
        List<Investment> expected = underTest.findAllByNameAndUsername(name,username);

        //Then
        assertThat(expected).isNotNull();
    }
}