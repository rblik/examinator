package com.db.schooolexaminator.services;

import com.db.schooolexaminator.dao.ConfigurationDao;
import com.db.schooolexaminator.dto.ConfDto;
import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.Email;
import com.db.schooolexaminator.model.OperationConstraint;
import com.db.schooolexaminator.model.Teacher;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;
import static org.junit.Assert.*;

/**
 * Created by Blik on 03/12/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-services-context.xml", "file:src/main/webapp/WEB-INF/hibernate-context.xml"})
public class ConfigurationServiceTest {

    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ConfigurationDao configurationDao;
    @Test
    public void delete() throws Exception {
        Teacher teacher = new Teacher("username", "password", new ArrayList<Configuration>());
        teacherService.add(teacher);
        OperationConstraint operationConstraint = new OperationConstraint();
        Configuration configuration = new Configuration("Title", ImmutableList.of(new Email("one@gmail.com")), singletonList(operationConstraint), 3,4);
        Configuration name = configurationService.addByName(teacher.getUserName(), configuration);
        System.out.println(name.getConfigurationId());
        /*System.out.println(configurationService.getByUserName("username"));
        configurationService.delete(name.getConfigurationId());
        System.out.println(configurationService.getByUserName("username"));*/
        List<ConfDto> username = configurationDao.findByName("username");
//        configurationDao.delete(1);
    }
}