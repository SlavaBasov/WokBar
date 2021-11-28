package com.basovProjects.wokBar.controller;

import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import com.basovProjects.wokBar.model.Product;
import com.basovProjects.wokBar.model.ShoppingCart;
import com.basovProjects.wokBar.model.ShoppingCartLineItem;
import com.basovProjects.wokBar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ShoppingCart shoppingCart;
    private final ProductService<Long,Product> productService;

    @Autowired
    public CartController(ShoppingCart shoppingCart, ProductService<Long, Product> productService) {
        this.shoppingCart = shoppingCart;
        this.productService = productService;
    }

    @GetMapping
    public String basket(Model model){
        model.addAttribute("cartProducts",
                new ArrayList<>(shoppingCart.getLineItems().values()));
        model.addAttribute("totalPrice", shoppingCart.getSubTotalCost());
        return "cart";
    }

    @GetMapping("/addQuantityTo{product_id}")
    public String addQuantity(@PathVariable("product_id") Long productId){
        shoppingCart.addLineItem(shoppingCart.getLineItems().get(productId));
        return "redirect:/cart";
    }

    @GetMapping("/subtractQuantityTo{product_id}")
    public String subtractQuantity(@PathVariable("product_id") Long productId){
        shoppingCart.subtractQuantity(shoppingCart.getLineItems().get(productId));
        return "redirect:/cart";
    }

    @GetMapping("/delete{product_id}")
    public String deleteLineItem(@PathVariable("product_id") Long productId){
        shoppingCart.deleteLineItem(productId);
        return "redirect:/cart";
    }
}
