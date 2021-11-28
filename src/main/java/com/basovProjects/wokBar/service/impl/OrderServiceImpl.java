package com.basovProjects.wokBar.service.impl;

import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import com.basovProjects.wokBar.model.*;
import com.basovProjects.wokBar.repository.LineItemRepository;
import com.basovProjects.wokBar.repository.OrderRepository;
import com.basovProjects.wokBar.service.OrderService;
import com.basovProjects.wokBar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service()
public class OrderServiceImpl implements OrderService {

    private final ShoppingCart shoppingCart;
    private final OrderRepository orderRepository;
    private final UserService<Long, User> userService;
    private final LineItemRepository lineItemRepository;
    private final HttpSession httpSession;

    public OrderServiceImpl(ShoppingCart shoppingCart, OrderRepository orderRepository, UserService<Long, User> userService, LineItemRepository lineItemRepository, HttpSession httpSession) {
        this.shoppingCart = shoppingCart;
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.lineItemRepository = lineItemRepository;
        this.httpSession = httpSession;
    }

    @Override
    public boolean formAnOrder() throws MyObjectNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Object principal = authentication.getPrincipal();

        User user = userService.findUserByUserName(name);

//        User userFound = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        if(shoppingCart==null){
            return false;
        }
        Order order = new Order();
        order.setUser(user);
        order.setTime(time);
        order.setDate(date);
        order.setTotalPrice(shoppingCart.getSubTotalCost());


        List<LineItem> lineItemList = new ArrayList<>();
        for (ShoppingCartLineItem shoppingCartLineItem: shoppingCart.getLineItems().values()){
            LineItem lineItem = new LineItem();
            lineItem.setProduct(shoppingCartLineItem.getProduct());
            lineItem.setQuantity(shoppingCartLineItem.getQuantity());
            lineItem.setOrder(order);
            lineItemList.add(lineItem);

        }

        order.setLineItems(lineItemList);

        orderRepository.save(order);
        lineItemRepository.saveAll(lineItemList);
        return true;

    }
}
