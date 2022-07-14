package com.InvestmentDashboard.UserService.Controller;

import com.InvestmentDashboard.UserService.Model.User;
import com.InvestmentDashboard.UserService.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController
{
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/adduser")
    public User adduser (@RequestBody User user)
    {
        return userService.adduser(user);
    }

    @GetMapping("/")
    public String login(@RequestParam String username, @RequestParam String password)
    {
        return userService.login(username,password);
    }
}
