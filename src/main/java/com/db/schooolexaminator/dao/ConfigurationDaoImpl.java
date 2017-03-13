package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.dto.ConfDto;
import com.db.schooolexaminator.model.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.util.stream.Collectors.toList;

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
        Configuration ref = entityManager.getReference(Configuration.class, id);
        entityManager.remove(ref);
    }

    @Override
    public List<ConfDto> findByName(String name) {
        return entityManager.createQuery("SELECT c.configurationId, c.title FROM Configuration c where c.teacher.userName=:userName", Object[].class)
                .setParameter("userName", name).getResultList().stream().map(ConfDto::new).collect(toList());
    }

    @Override
    public Configuration save(Configuration configuration) {
        entityManager.persist(configuration);
        return configuration;
    }
}
