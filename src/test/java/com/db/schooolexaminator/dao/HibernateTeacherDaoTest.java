package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.Constraint;
import com.db.schooolexaminator.model.Email;
import com.db.schooolexaminator.model.Teacher;
import com.db.schooolexaminator.services.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-services-context.xml")
public class HibernateTeacherDaoTest {

    @Autowired
    private TeacherService teacherService;

    @Test
    public void addConfiguration() throws Exception {
        Teacher teacher = new Teacher("username", "password", new ArrayList<Configuration>());
        teacherService.addUser(teacher);
        Constraint constraint = new Constraint();
        Configuration configuration = new Configuration("Title", Arrays.asList(new Email("one@gmail.com")),Arrays.asList(constraint), 3,4);
        teacherService.addConfiguration(teacher, configuration);
        List<Configuration> configurations = teacherService.getConfigurations(teacher.getUserName());
        System.out.println(configurations);
    }

}