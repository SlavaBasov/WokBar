package com.basovProjects.wokBar.service;

import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import com.basovProjects.wokBar.model.Order;

import java.util.List;

public interface OrderService {
    boolean formAnOrder() throws MyObjectNotFoundException;

    List<Order> getAllOrders();

    boolean setStatus(Long orderId, String status) throws MyObjectNotFoundException;

    Order getById(Long id) throws MyObjectNotFoundException;

    boolean delete(Long id);
}
