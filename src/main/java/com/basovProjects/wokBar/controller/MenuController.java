package com.basovProjects.wokBar.controller;

import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import com.basovProjects.wokBar.model.Product;
import com.basovProjects.wokBar.model.ShoppingCart;
import com.basovProjects.wokBar.model.ShoppingCartLineItem;
import com.basovProjects.wokBar.service.ProductService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final ProductService<Long, Product> productService;
    private final ShoppingCart shoppingCart;

    @Autowired
    public MenuController(ProductService<Long, Product> productService, ShoppingCart shoppingCart) {
        this.productService = productService;
        this.shoppingCart = shoppingCart;
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

    @GetMapping("/{categoryName}/add_to_basket{product_id}")
    public String addToBasket(@PathVariable("product_id") Long productId,
                              @PathVariable("categoryName") String categoryName) throws MyObjectNotFoundException {
        Product product = productService.getProductById(productId);
        shoppingCart.addLineItem(new ShoppingCartLineItem(product, 1, product.getPrice()));
        return String.format("redirect:/menu/%s", categoryName);
    }


}
