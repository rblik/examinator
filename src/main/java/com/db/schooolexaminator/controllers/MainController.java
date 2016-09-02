package com.db.schooolexaminator.controllers;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.SpecificNumber;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 31.08.2016.
 */

@Controller
@RequestMapping("/")
public class MainController {
    private static final String HARDCODED_JSON = "{\"title\":\"TestTitle\"," +
            "\"emails\":[\"abc@gmail.com\",\" def@adf.com\"]," +
            "\"frameRows\":\"4\"," +
            "\"frameCols\":\"3\"," +
            "\"constraints\":[" +
            "{\"sign\":\"+\",\"minAnswer\":\"1\",\"maxAnswer\":\"10\",\"minA\":\"1\",\"maxA\":\"10\",\"except\":[\"2\",\"3\"],\"minB\":\"1\",\"maxB\":\"10\"}," +
            "{\"sign\":\"-\",\"allowedNegativeAnswer\":\"true\",\"minA\":\"1\",\"maxA\":\"10\",\"except\":[\"2\",\"3\"],\"minB\":\"1\",\"maxB\":\"10\"}," +
            "{\"sign\":\"*\",\"minAnswer\":\"1\",\"maxAnswer\":\"10\",\"minA\":\"1\",\"maxA\":\"10\",\"except\":[\"2\",\"3\"],\"minB\":\"1\",\"maxB\":\"10\"}," +
            "{\"sign\":\"/\",\"minAnswer\":\"1\",\"maxAnswer\":\"10\",\"divisionWithoutRemainder\":\"true\",\"minA\":\"1\",\"maxA\":\"10\",\"except\":[\"2\",\"3\"],\"minB\":\"1\",\"maxB\":\"10\"}" +
            "]}";
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
        configurationJson = HARDCODED_JSON;
        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration = mapper.readValue(configurationJson, Configuration.class);
        System.out.println(configuration);
        Teacher teacher = new Teacher("username", "password", new ArrayList<>());
        teacher.getConfigurations().add(configuration);
        teacherService.addUser(teacher);
        teacherService.addConfiguration(teacher, configuration);
        List<Configuration> sameConfigurations = teacherService.getConfigurations("username");
        System.out.println(sameConfigurations.get(0));
        return "userconfigurations";
    }
}
