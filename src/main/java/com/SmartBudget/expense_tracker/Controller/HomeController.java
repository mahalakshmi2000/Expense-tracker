package com.SmartBudget.expense_tracker.Controller;

import com.SmartBudget.expense_tracker.Model.User;
import com.SmartBudget.expense_tracker.Repository.UserRepository;
import com.SmartBudget.expense_tracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @GetMapping
    public String hello() {
        return "Hello, World!";
    }


}
