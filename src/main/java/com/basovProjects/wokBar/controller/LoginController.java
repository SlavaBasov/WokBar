package com.basovProjects.wokBar.controller;

import com.basovProjects.wokBar.model.User;
import com.basovProjects.wokBar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
//@RequestMapping("/login")
public class LoginController{

    private UserService<Long, User> userService;

    @Autowired
    public LoginController(UserService<Long, User> userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("userLogin", new User());
        return "login";
    }

    @GetMapping("/login?error")
    public String loginError(Model model, @RequestParam("error") String errorMessage){
//        model.addAttribute("userLogin", user);
        errorMessage = "error MEss";
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

//    @GetMapping("/login-error")
//    public String loginError(Model model) {
//    public String login(HttpServletRequest request, Model model) {
//        HttpSession session = request.getSession(false);
//        String errorMessage = null;
//        if (session != null) {
//            AuthenticationException ex = (AuthenticationException) session
//                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//            if (ex != null) {
//                errorMessage = ex.getMessage();
//            }
//        }
//        model.addAttribute("errorMessage", "errorMessage");
//        return "login";
//    }



}
