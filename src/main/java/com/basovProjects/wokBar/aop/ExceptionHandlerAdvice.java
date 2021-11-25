package com.basovProjects.wokBar.aop;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({DataIntegrityViolationException.class})
    public String dataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex, Model model){
        System.out.println(ex);
        model.addAttribute("message","Such name already exists");
        model.addAttribute("exName", ex.getClass().getSimpleName());
        return "error/500";
    }
}
