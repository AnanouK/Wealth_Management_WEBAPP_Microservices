package com.InvestmentDashboard.InvestmentService.Repository;

import com.InvestmentDashboard.InvestmentService.Model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentRepository extends JpaRepository <Investment, Integer> {


    List<Investment> findAllByUsername(String username);

    List<Investment> findAllByNameAndUsername(String name, String Username);

    void deleteByUsername(String username);
}
