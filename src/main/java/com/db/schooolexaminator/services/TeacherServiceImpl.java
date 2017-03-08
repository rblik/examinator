package com.db.schooolexaminator.services;

import com.db.schooolexaminator.dao.TeacherDao;
import com.db.schooolexaminator.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherDao dao;

    @Override
    public void add(Teacher teacher) {
        dao.add(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        dao.update(teacher);
    }
}
