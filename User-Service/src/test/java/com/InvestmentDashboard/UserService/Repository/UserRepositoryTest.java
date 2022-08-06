package com.InvestmentDashboard.UserService.Repository;

import com.InvestmentDashboard.UserService.Model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void findByUsername() {
        //given
        String username = "jack";
        Users user = new Users(1,username,"jack@gmail.com","123");
        underTest.save(user);
        //when
        Users response = underTest.findByUsername(username);
        //then
        assertEquals(response,user);
    }
}