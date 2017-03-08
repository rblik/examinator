package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.OperationConstraint;
import com.db.schooolexaminator.model.Teacher;

import java.util.List;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public interface TeacherDao {
    Teacher findByName(String userName);

    Teacher update(Teacher teacher);
    void add(Teacher teacher);
}
