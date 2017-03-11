package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.model.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */

@Repository
public class ConfigurationDaoImpl implements ConfigurationDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Configuration get(int id) {
        List<Configuration> resultList = entityManager.createQuery("SELECT DISTINCT c FROM Configuration c LEFT JOIN FETCH c.operationConstraints WHERE c.configurationId=:id", Configuration.class)
                .setParameter("id", id)
                .getResultList();
//        return entityManager.find(Configuration.class, id);
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
