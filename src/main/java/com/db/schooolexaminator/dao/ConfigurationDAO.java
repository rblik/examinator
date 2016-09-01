package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.Constraint;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */

@Repository
public class ConfigurationDAO {
    public Configuration getConfigurationbyId(int configurationId) {
        Constraint c1 = new Constraint("+", 0, 100, 0, 100, 0, 1000, null, null, false, false, 123);
        Constraint c2 = new Constraint("*", 5, 20, 5, 20, 0, 10000000, null, null, false, false, 178);
        return new Configuration("privet", null,Arrays.asList(c1, c2), 3, 3);
    }
}
