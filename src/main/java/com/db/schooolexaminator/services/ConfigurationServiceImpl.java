package com.db.schooolexaminator.services;

import com.db.schooolexaminator.dao.ConfigurationDao;
import com.db.schooolexaminator.dao.TeacherDao;
import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.Teacher;
import com.db.schooolexaminator.security.AuthorizedUser;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blik on 03/08/2017.
 */
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private ConfigurationDao configurationDao;

    @Override
    public List<Configuration> getByUserName(String userName) {
        Teacher teacher = teacherDao.findByName(userName);
        return teacher.getConfigurations();
    }

    @Override
    @Transactional
    public Configuration addByName(Configuration configuration) {
        String teacherName = AuthorizedUser.getLoggedName();
        Teacher persistedTeacher = teacherDao.findByName(teacherName);

        persistedTeacher.getConfigurations().add(configuration);
        return Iterables.getLast(teacherDao.update(persistedTeacher).getConfigurations());
    }

    @Override
    public Configuration get(int id) {
        return configurationDao.get(id);
    }
}
