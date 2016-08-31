package com.db.schooolexaminator.controllers;

import com.db.schooolexaminator.model.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by JavaSchoolStudent on 31.08.2016.
 */


@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String getUserConfigurations(ModelMap model) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("qw", SecurityContextHolder.getContext().getAuthentication().getName());
        return "userconfigurations";
    }
}
