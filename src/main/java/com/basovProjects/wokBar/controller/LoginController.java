package com.basovProjects.wokBar.controller;

import com.basovProjects.wokBar.model.User;
import com.basovProjects.wokBar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController{

    private UserService<Long, User> userService;

    @Autowired
    public LoginController(UserService<Long, User> userService) {
        this.userService = userService;
    }

    @GetMapping
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

}
