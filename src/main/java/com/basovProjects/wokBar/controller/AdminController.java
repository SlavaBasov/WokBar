package com.basovProjects.wokBar.controller;

import com.basovProjects.wokBar.model.Category;
import com.basovProjects.wokBar.model.User;
import com.basovProjects.wokBar.service.CategoryService;
import com.basovProjects.wokBar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService<Long, User> userService;
    private final CategoryService<Long, Category> categoryService;

    @Autowired
    public AdminController(UserService<Long, User> userService, CategoryService<Long, Category> categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
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

    @GetMapping("/categories")
    public String categories(Model model){
//        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "/admin/categories";
    }

//    @GetMapping("/categories")
//    public String categories(HttpSession session, Model model){
//        String locale = (String) session.getAttribute("lang");
//        if(locale==null){
//            session.setAttribute("lang", "ru");
//            locale = (String) session.getAttribute("lang");
//        }
//
//        model.addAttribute("categories", categoryService.findAllCategories());
//        return "/admin/categories";
//    }

    @GetMapping("/categoryForm")
    public String categoryForm(Model model){
        model.addAttribute("category", new Category());
        return "admin/categoryForm";
    }

    @PostMapping("/categoryForm")
    public String addNewCategory(@ModelAttribute Category category){
        boolean check = categoryService.save(category);
        if(!check){
            return "/admin/categoryForm";
        }
        return "/admin/categories";
    }

}
