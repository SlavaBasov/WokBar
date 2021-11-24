package com.basovProjects.wokBar.controller.admin;

import com.basovProjects.wokBar.model.category.Category;
import com.basovProjects.wokBar.model.User;
import com.basovProjects.wokBar.model.category.CategoryTranslate;
import com.basovProjects.wokBar.service.CategoryService;
import com.basovProjects.wokBar.service.CategoryTranslateService;
import com.basovProjects.wokBar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService<Long, User> userService;
    private final CategoryService<Long, Category> categoryService;
    private final CategoryTranslateService<Long, CategoryTranslate> categoryTranslateService;

    @Autowired
    public AdminController(UserService<Long, User> userService,
                           CategoryService<Long, Category> categoryService,
                           CategoryTranslateService<Long, CategoryTranslate> categoryTranslateService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.categoryTranslateService = categoryTranslateService;
    }

    @GetMapping
    public String admin(){
        return "/admin/adminPage";
    }

    @GetMapping("/allUsers")
    public String allUsers(Model model){
        model.addAttribute("allUsers", userService.allUsers());
        return "/admin/allUsers";
    }



}
