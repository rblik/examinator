package com.db.schooolexaminator.controllers;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.Teacher;
import com.db.schooolexaminator.services.ConfigurationService;
import com.db.schooolexaminator.services.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JavaSchoolStudent on 31.08.2016.
 */

@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ConfigurationService configurationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUserConfigurations(ModelMap model) {
        return "createconfiguration";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @SneakyThrows
    public String addConfiguration(@RequestBody Configuration configuration, ModelMap model) {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Configuration configuration1 = configurationService.addByName(configuration);

        model.addAttribute("configurationId", configuration1.getConfigurationId());

        return "userconfigurations";
    }

    @RequestMapping(value = "/config/{id}", method = RequestMethod.GET)
    @SneakyThrows
    public String getLastConfiguration(@PathVariable("id") int id, ModelMap model) {

        Configuration resultConfiguration = configurationService.get(id);

        String resultJSON = new ObjectMapper().writeValueAsString(resultConfiguration);
        model.addAttribute("configuration", resultJSON);
        model.addAttribute("id", id);

        return "showconfiguration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {
        model.addAttribute("error", error);
        model.addAttribute("message", message);
        model.addAttribute("login", true);
        return "auth";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(ModelMap model) {
        model.addAttribute("teacher", new Teacher("", ""));
        return "auth";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.add(teacher);
        return "redirect:/";
    }
}
