package com.basovProjects.wokBar.controller.admin;

import com.basovProjects.wokBar.model.User;
import com.basovProjects.wokBar.model.category.Category;
import com.basovProjects.wokBar.model.category.CategoryTranslate;
import com.basovProjects.wokBar.service.CategoryService;
import com.basovProjects.wokBar.service.CategoryTranslateService;
import com.basovProjects.wokBar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final UserService<Long, User> userService;
    private final CategoryService<Long, Category> categoryService;
    private final CategoryTranslateService<Long, CategoryTranslate> categoryTranslateService;

    @Autowired
    public AdminCategoryController(UserService<Long, User> userService,
                           CategoryService<Long, Category> categoryService,
                           CategoryTranslateService<Long, CategoryTranslate> categoryTranslateService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.categoryTranslateService = categoryTranslateService;
    }

    @GetMapping
    public String categories(Model model){
        model.addAttribute("categories", categoryService.findAllCategories());
        return "admin/categories/categories";
    }

    @GetMapping("/newCategoryForm")
    public String categoryForm(Model model){
        model.addAttribute("category", new Category());
        return "/admin/categories/newCategoryForm";
    }

    @PostMapping("/newCategoryForm")
    public String addNewCategory(@ModelAttribute Category category){
        boolean check = categoryService.save(category);
        if(!check){
            return "/admin/categories/newCategoryForm";
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/categoryEditForm")
    public String categoryEditForm(@RequestParam("id") Long id, Model model){
        model.addAttribute("categoryEdit", categoryService.findById(id));
        return "admin/categories/categoryEditForm";
    }

    @PostMapping("/categoryEditForm")
    public String editCategory(@ModelAttribute Category category){
        boolean check = categoryService.update(category);
        if(!check){
            return "admin/categories/categoryEditForm";
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/categoryEditTranslationForm")
    public String categoryEditLocalizationForm(@RequestParam("id") Long id, Model model){
        CategoryTranslate categoryTranslateRu = categoryTranslateService.findTranslationToRussianByCategoryId(id);
        model.addAttribute("categoryTranslateRu", categoryTranslateRu);
        return "/admin/categories/categoryEditTranslationForm";
    }

    @PostMapping("/categoryEditTranslationForm")
    public String editCategory(@ModelAttribute CategoryTranslate categoryTranslate){
        boolean check = false;
        check = categoryTranslateService.update(categoryTranslate);

        if(!check){
            return "/admin/categories/categoryEditTranslationForm";
        }
        return "redirect:/admin/categories";
    }


}
