package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Teacher> findByName(String userName) {
//        Query query = entityManager.createQuery("select t from Teacher as t where t.userName=:userName");
//        List<Teacher> teachers = query.setParameter("userName", userName).getResultList();

        Query query = entityManager.createQuery("from Teacher");
        List<Teacher> teachers = query.getResultList();

        return teachers;
    }

    @Override
    public void updateUser(Teacher teacher) {
        entityManager.merge(teacher);
    }

    @Override
    public void addUser(Teacher teacher) {
        entityManager.persist(teacher);
    }
}
