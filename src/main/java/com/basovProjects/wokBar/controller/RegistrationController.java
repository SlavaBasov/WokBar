package com.basovProjects.wokBar.controller;

import com.basovProjects.wokBar.enums.Roles;
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
        System.out.println("Вошли в registrationProcess");
        if (bindingResult.hasErrors()) {
            System.out.println("Есть ошибка, отправление на registration.jsp");
            return "registration";
        }
        System.out.println("Нет ошибки, отправление на hello.jsp");
        if (userService.findUserByUsername(user.getUsername()) != null) {
            model.addAttribute("identificationMessage",
                    "user with the same name already exists");
            model.addAttribute("user", user);
            return "registration";
        }
        user.setRoles(new HashSet<>(Collections.singletonList(roleServise.findByName(Roles.USER.toString()))));
        userService.save(user);
        return "redirect:/";
    }
}

