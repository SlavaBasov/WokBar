package com.basovProjects.wokBar.service;

import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;

public interface OrderService {
    boolean formAnOrder() throws MyObjectNotFoundException;
}
