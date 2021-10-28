package com.basovProjects.wokBar.controller;

import com.basovProjects.wokBar.model.Role;
import com.basovProjects.wokBar.model.User;
import com.basovProjects.wokBar.service.RoleServise;
import com.basovProjects.wokBar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService<Long, User> userService;
    private final RoleServise<Long, Role> roleServise;

    @Autowired
    public RegistrationController(UserService<Long, User> userService, RoleServise<Long, Role> roleServise) {
        this.userService = userService;
        this.roleServise = roleServise;
    }

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("userRegistration", new User());
        return "registration";
    }


    @PostMapping
    public String addNewUser(@ModelAttribute("userRegistration") @Valid User user
            , BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
//        if (!user.getPassword().equals(user.getPasswordConfirm())){
//            model.addAttribute("passwordError", "Пароли не совпадают");
//            return "registration";
//        }
        if (!userService.save(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/";
    }
}

