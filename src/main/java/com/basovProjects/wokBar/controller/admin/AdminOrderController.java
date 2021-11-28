package com.basovProjects.wokBar.controller.admin;

import com.basovProjects.wokBar.enums.OrderStatus;
import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import com.basovProjects.wokBar.model.Order;
import com.basovProjects.wokBar.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;

    @Autowired
    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String orders(Model model){
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/orders/orders";
    }

    @GetMapping("/status_paid{order_id}")
    public String statusPaid(@PathVariable("order_id") Long orderId) throws MyObjectNotFoundException {
        orderService.setStatus(orderId, OrderStatus.PAID.toString());
        return "redirect:/admin/orders";
    }

    @GetMapping("/status_completed{order_id}")
    public String statusCompleted(@PathVariable("order_id") Long orderId) throws MyObjectNotFoundException {
        orderService.setStatus(orderId, OrderStatus.COMPLETED.toString());
        return "redirect:/admin/orders";
    }

    @GetMapping("/delete_order{order_id}")
    public String deleteOrder(@PathVariable("order_id") Long orderId) {
        orderService.delete(orderId);
        return "redirect:/admin/orders";
    }
}
