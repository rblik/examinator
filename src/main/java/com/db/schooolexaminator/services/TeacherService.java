package com.db.schooolexaminator.services;

import com.db.schooolexaminator.dao.UserDao;
import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.OperationConstraint;
import com.db.schooolexaminator.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@Service
@Transactional
public class TeacherService {
    @Autowired
    private UserDao dao;

    public List<Configuration> getConfigurations(String userName) {
        Teacher teacher = dao.findByName(userName).get(0);
        return teacher.getConfigurations();
    }

//    public List<Teacher> getTeacherByName(String userName) {
//        return dao.findByName(userName).get(0);
//    }

    public void addConfiguration(Teacher teacher, Configuration configuration) {
        teacher.getConfigurations().add(configuration);
        dao.updateUser(teacher);
    }

    public void addUser(Teacher teacher) {
        dao.addUser(teacher);
    }

    public void updateUser(Teacher teacher) {
        dao.updateUser(teacher);
    }

    public Configuration getConfigurationById(int id) {
        Configuration configuration = dao.getConfigurationById(id);
        return configuration;
    }

}
