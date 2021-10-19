package com.basovProjects.wokBar.controller;

import com.basovProjects.wokBar.model.User;
import com.basovProjects.wokBar.service.UserService;
import net.bytebuddy.matcher.StringMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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

    //ДОПИЛИТЬ!!!!!!!!!!!!!!!!!!!!!!!!
    @PostMapping
    public String LoginUser(@ModelAttribute("userLogin") User user,
                            BindingResult bindingResult, Model model){
        User userFoundByUsername = userService.findUserByUsername(user.getUsername());
        if(userFoundByUsername==null){
            model.addAttribute("notValidUsernameMessage", "such user does not exist");
            return "login";
        }
        if(!userFoundByUsername.getPassword().equals(user.getPassword())){
            model.addAttribute("notValidPasswordMessage", "wrong password");
            return "login";
        }
        return null;
    }
}
