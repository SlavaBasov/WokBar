package com.basovProjects.wokBar.controller;

import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import com.basovProjects.wokBar.model.ShoppingCart;
import com.basovProjects.wokBar.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart/order")
public class OrderController {

    private final OrderService orderService;
    private final ShoppingCart shoppingCart;

    @Autowired
    public OrderController(OrderService orderService, ShoppingCart shoppingCart) {
        this.orderService = orderService;
        this.shoppingCart = shoppingCart;
    }

    @GetMapping
    public String formAnOrder() throws MyObjectNotFoundException {
        orderService.formAnOrder();
        shoppingCart.deleteAllLineItems();
        return "redirect:/cart/order/access-order";
    }

    @GetMapping("/access-order")
    public String accessOrder(){
        return "accessOrderPage";
    }
}
