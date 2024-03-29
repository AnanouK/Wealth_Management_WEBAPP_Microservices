package com.InvestmentDashboard.UserService.Controller;

import com.InvestmentDashboard.UserService.Model.Users;
import com.InvestmentDashboard.UserService.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/add")
    public String adduser (@RequestBody Users user)
    {
        return userService.addUser(user);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public String login(@RequestParam String username, @RequestParam String password)
    {
        return userService.login(username,password);
    }
}
