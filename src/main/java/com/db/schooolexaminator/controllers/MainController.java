package com.db.schooolexaminator.controllers;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.Teacher;
import com.db.schooolexaminator.services.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 31.08.2016.
 */

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public String getUserConfigurations(ModelMap model) {
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        List<Configuration> configurations = teacherService.getConfigurations(userName);
//
//        model.addAttribute("configurations", configurations);

//        model.addAttribute("qw", SecurityContextHolder.getContext().getAuthentication().getName());
        return "createconfiguration";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @SneakyThrows
    public String addConfiguration(@RequestBody String configurationJson) {
        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration = mapper.readValue(configurationJson, Configuration.class);
        System.out.println(configuration);
        Teacher teacher = new Teacher("username", "password", new ArrayList<>());
//        teacherService.addConfiguration(teacher, configuration);
        return "userconfigurations";
    }
}
