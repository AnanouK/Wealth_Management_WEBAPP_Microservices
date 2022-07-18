package com.InvestmentDashboard.UserService.Service;

import com.InvestmentDashboard.UserService.Model.User;
import com.InvestmentDashboard.UserService.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String adduser(User user)
    {
        if(userRepository.findByUsername(user.getUsername()) != null)
        {
            return "Impossible, nom d'utilisateur existant";
        }
        else {
            userRepository.save(user);
            return "Création de l'utilisateur avec success";
        }
    }

    public String login(String username, String password)
    {
        if (userRepository.findByUsername(username) != null){
            User user = userRepository.findByUsername(username);
            String passworduser = user.getPassword();
            if (passworduser.equals(password)){
                return "success";
            }

            else return "fail";
        }

        else return "fail";
    }
}
