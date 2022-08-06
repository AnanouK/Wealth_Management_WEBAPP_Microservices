package com.InvestmentDashboard.UserService.Repository;

import com.InvestmentDashboard.UserService.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {


    Users findByUsername(String username);
}
