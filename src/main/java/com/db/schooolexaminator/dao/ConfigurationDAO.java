package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.OperationConstraint;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */

@Repository
public class ConfigurationDAO {
    public Configuration getConfigurationbyId(int configurationId) {
        OperationConstraint c1 = new OperationConstraint("+", 0, 100, 0, 100, 0, 1000, null, null, null, null, false, false, 123);
        OperationConstraint c2 = new OperationConstraint("*", 5, 20, 5, 20, 0, 10000000, null, null, null, null, false, false, 178);
        return new Configuration("privet", null,Arrays.asList(c1, c2), 3, 3);
    }
}
