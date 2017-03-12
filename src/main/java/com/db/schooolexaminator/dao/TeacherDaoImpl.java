package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@Repository
public class TeacherDaoImpl implements TeacherDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Teacher findByName(String userName) {

        List<Teacher> resultList = entityManager
                .createQuery("select t from Teacher as t where t.userName=:userName", Teacher.class)
                .setParameter("userName", userName).getResultList();
        return resultList.isEmpty()? null : resultList.get(0);
    }

    @Override
    @Transactional
    public Teacher update(Teacher teacher) {
        return entityManager.merge(teacher);
    }

    @Override
    @Transactional
    public void add(Teacher teacher) {
        entityManager.persist(teacher);
    }
}
