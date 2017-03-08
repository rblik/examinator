package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.model.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */

@Repository
public class ConfigurationDaoImpl implements ConfigurationDaoI {
    @PersistenceContext
    private EntityManager entityManager;

//    public Configuration getConfigurationbyId(int configurationId) {
//        OperationConstraint c1 = new OperationConstraint("+", 0, 100, 0, 100, 0, 1000, null, null, null, null, false, false, 123);
//        OperationConstraint c2 = new OperationConstraint("*", 5, 20, 5, 20, 0, 10000000, null, null, null, null, false, false, 178);
//
//        Email e1 = new Email("maxim.usmanov23@gmail.com");
//        Email e2 = new Email("fedor.korchaznikov@gmail.com");
//        Email e3 = new Email("watson9494@gmail.com");
//
//        return new Configuration("Plus/Multiplication Exam", Arrays.asList(e1, e2, e3),Arrays.asList(c1, c2), 3, 3);
//    }

    @Override
    public Configuration get(int id) {
        return entityManager.find(Configuration.class, id);
    }
}
