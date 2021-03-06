package com.db.schooolexaminator.controllers;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.Picture;
import com.db.schooolexaminator.model.Teacher;
import com.db.schooolexaminator.services.ConfigurationService;
import com.db.schooolexaminator.services.PictureService;
import com.db.schooolexaminator.services.TeacherService;
import com.db.schooolexaminator.telegramserver.picture.PictureManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import static com.db.schooolexaminator.security.AuthorizedUser.current_user;

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
    @Autowired
    private PictureManager pictureManager;
    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getUserConfigurations(ModelMap model) {
        return "createconfiguration";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SneakyThrows
    public String addConfiguration(@RequestPart(name = "file", required = false) MultipartFile file, String configurationJson, ModelMap model) {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Configuration configuration = new ObjectMapper().readValue(configurationJson, Configuration.class);
//        pictureManager.savePicture(configuration, file);

        configuration.setHasImage(file != null);
        Configuration confPersisted = configurationService.addByName(current_user(), configuration);
        if (file != null) {
            pictureService.save(new Picture(confPersisted, file.getBytes()));
        }
        model.addAttribute("configurationId", confPersisted.getConfigurationId());

        return "userconfigurations";
    }

    @SneakyThrows
    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public void getPictureByConfId(@PathVariable("id") int id, HttpServletResponse response) {
        Picture picture = pictureService.getByConf(id);
        if (picture != null) {
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            ServletOutputStream stream = response.getOutputStream();
            stream.write(picture.getContent());
            stream.flush();
            stream.close();
        }
    }

    @RequestMapping(value = "/config/{id}", method = RequestMethod.GET)
    @SneakyThrows
    public String getLastConfiguration(@PathVariable("id") int id, ModelMap model) {

        Configuration resultConfiguration = configurationService.get(id);

        String resultJSON = new ObjectMapper().writeValueAsString(resultConfiguration);
        model.addAttribute("configuration", resultJSON);
        model.addAttribute("id", id);
        model.addAttribute("hasImage", resultConfiguration.isHasImage());
        model.addAttribute("picName", resultConfiguration.getPicName());

        return "showconfiguration";
    }

    @DeleteMapping(value = "/config/{id}")
    public ResponseEntity deleteConfiguration(@PathVariable("id") int id) {
        configurationService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/")
    public String getAllConfigurations(ModelMap model) {
        model.addAttribute("list", configurationService.getByUserName(current_user()));
        model.addAttribute("currentUser", current_user());
        return "listconfigurations";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {
        model.addAttribute("error", error);
        model.addAttribute("message", "registered".equals(message) ? "You successfully registered" : null);
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
        return "redirect:login?message=registered";
    }
}
