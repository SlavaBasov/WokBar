package com.basovProjects.wokBar.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    private static final Logger log = LogManager.getLogger(MainController.class.getName());

    @GetMapping("/")
    public String mainPage(Model model){
        SecurityContext context = SecurityContextHolder.getContext();

        model.addAttribute("message", "You are logged in as "
                + context.getAuthentication().getName());
        log.debug("Debug Message Logged !!!");
        log.info("Info Message Logged !!!");
//        log.error("Error Message Logged !!!", new NullPointerException("NullError"));
        return "mainPage";
    }
}
