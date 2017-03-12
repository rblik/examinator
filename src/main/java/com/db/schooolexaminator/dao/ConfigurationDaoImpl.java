package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Arrays;
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

    @Override
    @Transactional
    public void delete(int id) {
        // TODO: 03/12/2017
        Configuration ref = entityManager.getReference(Configuration.class, id);
        entityManager.remove(ref);
    }

    @Override
    public List<Configuration> findByName(String name) {
        // TODO: 03/12/2017
//        List<List<Configuration> list = Arrays.asList(entityManager.createQuery("SELECT t.configurations FROM Teacher t where t.userName=:userName", List.class)
//                .setParameter("userName", name).getResultList());
        return null;
    }
}
