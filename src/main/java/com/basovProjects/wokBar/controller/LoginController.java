package com.basovProjects.wokBar.controller;

import com.basovProjects.wokBar.model.User;
import com.basovProjects.wokBar.service.i18n.MessageByLocalService;
import com.basovProjects.wokBar.service.i18n.MessageByLocalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController{

    private final MessageByLocalService messageByLocalService;

    @Autowired
    public LoginController(MessageByLocalServiceImpl messageByLocalService) {
        this.messageByLocalService = messageByLocalService;
    }

    @GetMapping
    public String login(@RequestParam(value = "error", defaultValue = "false") boolean loginError, Model model){
        if(loginError){
            String errorMessage = messageByLocalService.getMessage("login.error");
            model.addAttribute("errorMessage", errorMessage);
        }
        model.addAttribute("userLogin", new User());
        return "login";
    }




}
