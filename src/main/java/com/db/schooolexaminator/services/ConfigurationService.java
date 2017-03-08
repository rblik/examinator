package com.db.schooolexaminator.services;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.Teacher;

import java.util.List;

/**
 * Created by Blik on 03/08/2017.
 */
public interface ConfigurationService {
    List<Configuration> getByUserName(String userName);

    Configuration getLastByUserName(String userName);

    void add(Teacher teacher, Configuration configuration);

    Configuration get(int id);
}
