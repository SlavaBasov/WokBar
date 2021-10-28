package com.basovProjects.wokBar.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(Model model){
        SecurityContext context = SecurityContextHolder.getContext();

        model.addAttribute("message", "You are logged in as "
                + context.getAuthentication().getName());
        return "mainPage";
    }
}
