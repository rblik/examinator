package com.db.schooolexaminator.services;

import com.db.schooolexaminator.dao.ConfigurationDao;
import com.db.schooolexaminator.dao.TeacherDao;
import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.Teacher;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Blik on 03/08/2017.
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private ConfigurationDao configurationDao;

    @Override
    public List<Configuration> getByUserName(String userName) {
        return configurationDao.findByName(userName);
    }

    @Override
    @Transactional
    public Configuration addByName(String teacherName, Configuration configuration) {
        Teacher persistedTeacher = teacherDao.findByName(teacherName);

        persistedTeacher.getConfigurations().add(configuration);
        return Iterables.getLast(teacherDao.update(persistedTeacher).getConfigurations());
    }

    @Override
    public Configuration get(int id) {
        return configurationDao.get(id);
    }

    @Override
    public void delete(int id) {
        configurationDao.delete(id);
    }
}
