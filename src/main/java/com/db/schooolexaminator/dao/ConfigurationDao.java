package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.dto.ConfDto;
import com.db.schooolexaminator.model.Configuration;

import java.util.List;

/**
 * Created by Blik on 03/08/2017.
 */
public interface ConfigurationDao {

    Configuration get(int id);

    void delete(int id);

    List<ConfDto> findByName(String name);

    Configuration save(Configuration configuration);
}
