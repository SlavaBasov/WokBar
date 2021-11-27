package com.basovProjects.wokBar.aop;

import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({DataIntegrityViolationException.class})
    public String dataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex, Model model){
        System.out.println(ex);
        model.addAttribute("errorMessage","Such name already exists");
        model.addAttribute("exName", ex.getClass().getSimpleName());
        return "error/500";
    }

    @ExceptionHandler({MyObjectNotFoundException.class})
    public String myObjectNotFoundExceptionHandler(MyObjectNotFoundException ex, Model model){
        System.out.println(ex);
        model.addAttribute("errorMessage",ex.getMessage());
        model.addAttribute("exName", ex.getClass().getSimpleName());
        return "error/500";
    }
}
