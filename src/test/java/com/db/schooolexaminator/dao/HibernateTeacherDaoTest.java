package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.dto.ConfDto;
import com.db.schooolexaminator.model.*;
import com.db.schooolexaminator.services.ConfigurationService;
import com.db.schooolexaminator.services.TeacherService;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.Files;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-services-context.xml", "file:src/main/webapp/WEB-INF/hibernate-context.xml"})
@ActiveProfiles({"local"})
public class HibernateTeacherDaoTest {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private PictureDao pictureDao;

    @Test
    public void addConfiguration() throws Exception {
        Teacher teacher = new Teacher("username", "password", new ArrayList<Configuration>());
        teacherService.add(teacher);
        OperationConstraint operationConstraint = new OperationConstraint();
        Configuration configuration = new Configuration("Title", ImmutableList.of(new Email("one@gmail.com")), Arrays.asList(operationConstraint), 3, 4);
        Configuration configuration3 = configurationService.addByName(teacher.getUserName(), configuration);
        ConfDto configuration1 = configurationService.getByUserName(teacher.getUserName()).get(0);
        Configuration configuration2 = configurationService.get(configuration1.getConfigurationId());

        Picture picture = new Picture();
        picture.setConfiguration(configuration2);
        picture.setContent(Files.toByteArray(new File("E:/temp/logo.jpg")));
        pictureDao.save(picture);
        Picture picture1 = pictureDao.get(configuration2.getConfigurationId());
        Files.write(picture1.getContent(), new File("E:/temp/logogo.jpg"));

    }

}