package com.db.schooolexaminator.controllers;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.OperationConstraint;
import com.db.schooolexaminator.model.Teacher;
import com.db.schooolexaminator.services.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by JavaSchoolStudent on 31.08.2016.
 */

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUserConfigurations(ModelMap model) {
        return "createconfiguration";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @SneakyThrows
    public String addConfiguration(ModelMap model, HttpServletRequest request) {
        String configurationJson = request.getParameter("data");
        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration = mapper.readValue(configurationJson, Configuration.class);

        Teacher teacher = new Teacher("username", "password", new ArrayList<>());
        teacher.getConfigurations().add(configuration);

        teacherService.updateUser(teacher);

        List<Configuration> sameConfigurations = teacherService.getConfigurations("username");
        Configuration latestConfiguration = sameConfigurations.get(sameConfigurations.size() - 1);

        model.addAttribute("configurationId",latestConfiguration.getConfigurationId());

        return "userconfigurations";
    }

    @RequestMapping(value = "/config/{id}", method = RequestMethod.GET)
    @SneakyThrows
    public String getLastConfiguration(@PathVariable("id") int id, ModelMap model) {

        Configuration resultConfiguration = teacherService.getConfigurationById(id);

        ObjectMapper objectMapper = new ObjectMapper();
        String resultJSON = objectMapper.writeValueAsString(resultConfiguration);
        model.addAttribute("configuration", resultJSON);
        model.addAttribute("id", id);

        return "showconfiguration";
    }
}
