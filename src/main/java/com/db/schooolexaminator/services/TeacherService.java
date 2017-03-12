package com.db.schooolexaminator.services;

import com.db.schooolexaminator.model.Teacher;

/**
 * Created by Blik on 03/08/2017.
 */
public interface TeacherService {
    void add(Teacher teacher);

    Teacher findByName(String userName);
}
