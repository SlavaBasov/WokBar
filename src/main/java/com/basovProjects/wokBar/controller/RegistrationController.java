package com.basovProjects.wokBar.controller;

import com.basovProjects.wokBar.model.User;
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

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService<Long, User> userService;

    @Autowired
    public RegistrationController(UserService<Long, User> userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("userRegistration", new User());
        return "registration";
    }

    @PostMapping
    public String addNewUser(@ModelAttribute("userRegistration") @Valid User user, Model model
            , BindingResult bindingResult) {
        System.out.println("Вошли в registrationProcess");
        if (bindingResult.hasErrors()) {
            System.out.println("Есть ошибка, отправление на registration.jsp");
            return "registration";
        }
        System.out.println("Нет ошибки, отправление на hello.jsp");
        return "hello";







//        else {
//            if (userService.findUserByUsername(user.getUsername()) != null) {
//                model.addAttribute("identificationMessage",
//                        "user with the same name already exists");
//                model.addAttribute("user", user);
//                return "redirect:/registration";
//            } else {
//                userService.save(user);
//                return "/";
//            }
//        }

    }
}

