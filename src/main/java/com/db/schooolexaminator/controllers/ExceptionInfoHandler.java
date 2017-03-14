package com.db.schooolexaminator.controllers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * Created by Blik on 03/09/2017.
 */
@ControllerAdvice
public class ExceptionInfoHandler {

    @ExceptionHandler(PersistenceException.class)
    @Order(Ordered.LOWEST_PRECEDENCE - 1)
    public String constraintViolationExceptionHandler(HttpServletRequest request, Exception ex) throws Exception {
        request.setAttribute("message", "User with this name already present in app");
        return "auth";
    }

    @ExceptionHandler(Exception.class)
    @Order(Ordered.LOWEST_PRECEDENCE)
    ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView("exception");
        mav.addObject("exception", e);
        return mav;
    }
}
