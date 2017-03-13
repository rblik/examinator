package com.db.schooolexaminator.services;

import com.db.schooolexaminator.dto.ConfDto;
import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.Teacher;

import java.util.List;

/**
 * Created by Blik on 03/08/2017.
 */
public interface ConfigurationService {
    List<ConfDto> getByUserName(String userName);

    Configuration addByName(String teacherName, Configuration configuration);

    Configuration get(int id);

    void delete(int id);
}
