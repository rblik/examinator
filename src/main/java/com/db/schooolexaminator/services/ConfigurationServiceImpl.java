package com.db.schooolexaminator.services;

import com.db.schooolexaminator.dao.ConfigurationDao;
import com.db.schooolexaminator.dao.TeacherDao;
import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Configuration getLastByUserName(String userName) {
// TODO: 03/08/2017 IMPLEMENT
        return null;
    }

    @Override
    public void add(Teacher teacher, Configuration configuration) {
        teacher.getConfigurations().add(configuration);
        teacherDao.update(teacher);
    }

    @Override
    public Configuration get(int id) {
        return configurationDao.get(id);
    }
}
