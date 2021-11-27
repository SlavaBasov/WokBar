package com.basovProjects.wokBar.controller;

import com.basovProjects.wokBar.model.Product;
import com.basovProjects.wokBar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final ProductService<Long, Product> productService;


    @Autowired
    public MenuController(ProductService<Long, Product> productService) {
        this.productService = productService;
    }

    @GetMapping("/wok")
    public String menuWok(Model model){
        model.addAttribute("products", productService.getAllProductsByCategoryId(1L));
        return "menu/menu";
    }

    @GetMapping("/beverages")
    public String menuBeverages(Model model){
        model.addAttribute("products", productService.getAllProductsByCategoryId(2L));
        return "menu/menu";
    }

    @GetMapping("/souses")
    public String menuSouses(Model model){
        model.addAttribute("products", productService.getAllProductsByCategoryId(3L));
        return "menu/menu";
    }


}
