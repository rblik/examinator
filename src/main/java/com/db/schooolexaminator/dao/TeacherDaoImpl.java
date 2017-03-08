package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@Repository
public class TeacherDaoImpl implements TeacherDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Teacher findByName(String userName) {

        return entityManager
                .createQuery("select t from Teacher as t where t.userName=:userName", Teacher.class)
                .setParameter("userName", userName).getResultList().get(0);
    }

    @Override
    public void update(Teacher teacher) {
        entityManager.merge(teacher);
    }

    @Override
    public void add(Teacher teacher) {
        entityManager.persist(teacher);
    }
}
