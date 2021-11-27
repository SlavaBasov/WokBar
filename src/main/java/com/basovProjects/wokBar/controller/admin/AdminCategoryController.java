package com.basovProjects.wokBar.controller.admin;

import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import com.basovProjects.wokBar.model.Language;
import com.basovProjects.wokBar.model.User;
import com.basovProjects.wokBar.model.category.Category;
import com.basovProjects.wokBar.model.category.CategoryTranslate;
import com.basovProjects.wokBar.service.CategoryService;
import com.basovProjects.wokBar.service.CategoryTranslateService;
import com.basovProjects.wokBar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

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
        model.addAttribute("categories", categoryService.findAllCategoriesWithLocalization());
        return "admin/categories/categories";
    }

    @GetMapping("/newCategoryForm")
    public String newCategoryForm(Model model){
        model.addAttribute("category", new Category());
        return "/admin/categories/newCategoryForm";
    }

    @PostMapping("/newCategoryForm")
    public String addNewCategory(@ModelAttribute @Valid Category category, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "/admin/categories/newCategoryForm";
        }
        if(!categoryService.save(category)){
            model.addAttribute("errorMessage", "this category already exists");
            return "/admin/categories/newCategoryForm";
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/categoryEditForm")
    public String categoryEditForm(@RequestParam("id") Long id, Model model) throws MyObjectNotFoundException {
        model.addAttribute("category", categoryService.findById(id));
        return "admin/categories/categoryEditForm";
    }

    @PostMapping("/categoryEditForm")
    public String editCategory(@ModelAttribute @Valid Category category, BindingResult bindingResult, Model model)
            throws MyObjectNotFoundException {
        if(bindingResult.hasErrors()){
            return "/admin/categories/categoryEditForm";
        }
        if(!categoryService.update(category)){
            model.addAttribute("category", categoryService.findById(category.getId()));
            model.addAttribute("errorMessage", "this category already exists");
            return "admin/categories/categoryEditForm";
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/categoryEditTranslationForm")
    public String categoryEditLocalizationForm(@RequestParam("id") Long id, Model model) throws MyObjectNotFoundException {
        CategoryTranslate categoryTranslate = categoryTranslateService.findByCategoryIdAndLanguageId(id, "ru");
        model.addAttribute("categoryTranslate", categoryTranslate);
        return "/admin/categories/categoryEditTranslationForm";
    }

    @PostMapping("/categoryEditTranslationRussianForm")
    public String editCategoryTranslate(@ModelAttribute CategoryTranslate categoryTranslate) throws MyObjectNotFoundException {
        boolean check;
        check = categoryTranslateService.update(categoryTranslate);

        if(!check){
            return "/admin/categories/categoryEditTranslationForm";
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam("id") Long id){
        categoryService.delete(id);
        return "redirect:/admin/categories";
    }




}
