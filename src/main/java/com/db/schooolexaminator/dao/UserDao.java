package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.model.OperationConstraint;
import com.db.schooolexaminator.model.Teacher;

import java.util.List;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public interface UserDao{
    List<Teacher> findByName(String userName);

    void updateUser(Teacher teacher);
    void addUser(Teacher teacher);

    void addConstraint(OperationConstraint operationConstraint);

    List<OperationConstraint> getAllConstraints();
}
