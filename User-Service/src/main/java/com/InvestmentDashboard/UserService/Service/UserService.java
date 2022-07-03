package com.InvestmentDashboard.UserService.Service;

import com.InvestmentDashboard.UserService.Model.User;
import com.InvestmentDashboard.UserService.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User adduser(User user)
    {
        return userRepository.save(user);
    }

    public Optional<User> finduserbyid(Integer id)
    {
        return userRepository.findById(id);
    }
}
