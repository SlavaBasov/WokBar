package com.basovProjects.wokBar.controller;

import com.basovProjects.wokBar.model.User;
import com.basovProjects.wokBar.service.UserService;
import com.basovProjects.wokBar.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService<Long, User> userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String admin(){
        return "/admin/adminPage";
    }

//    @PostMapping
//    public String admin(){
//        return "";
//    }

    @GetMapping("allUsers")
    public String allUsers(Model model){
        model.addAttribute("allUsers", userService.allUsers());
        return "/admin/allUsers";
    }

}
