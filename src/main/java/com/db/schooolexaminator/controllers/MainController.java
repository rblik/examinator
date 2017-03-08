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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

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
//        String configurationJson = request.getParameter("data");
//        ObjectMapper mapper = new ObjectMapper();
//        Configuration configuration = mapper.readValue(configurationJson, Configuration.class);

        Teacher teacher = new Teacher("username", "password", new ArrayList<>());
        teacher.getConfigurations().add(configuration);

        teacherService.update(teacher);

        List<Configuration> sameConfigurations = configurationService.getByUserName("username");
        Configuration latestConfiguration = sameConfigurations.get(sameConfigurations.size() - 1);

        model.addAttribute("configurationId",latestConfiguration.getConfigurationId());

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
}
