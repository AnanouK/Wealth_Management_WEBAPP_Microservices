package com.InvestmentDashboard.UserService.Service;

import com.InvestmentDashboard.UserService.Model.Users;
import com.InvestmentDashboard.UserService.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String addUser(Users user)
    {
        if(userRepository.findByUsername(user.getUsername()) != null)
        {
            return "Impossible, nom d'utilisateur existant";
        }
        else {
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return "Création de l'utilisateur avec success";
        }
    }

    public String login(String username, String password)
    {
        Users user = userRepository.findByUsername(username);

        if (user != null){
            String passwordUser = user.getPassword();
            if (passwordUser.equals(password)){
                return "success";
            }

            else return "fail";
        }

        else return "fail";
    }
}
