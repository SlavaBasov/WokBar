package com.basovProjects.wokBar.controller;

import com.basovProjects.wokBar.model.Role;
import com.basovProjects.wokBar.model.User;
import com.basovProjects.wokBar.service.RoleServise;
import com.basovProjects.wokBar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class MainController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView sayHello(ModelAndView modelAndView){
        modelAndView.setViewName("mainPage");
        return modelAndView;
    }
}
