package com.db.schooolexaminator.services;

import com.db.schooolexaminator.dao.TeacherDao;
import com.db.schooolexaminator.model.Teacher;
import com.db.schooolexaminator.security.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@Service
public class TeacherServiceImpl implements TeacherService, UserDetailsService {
    @Autowired
    private TeacherDao dao;

    @Override
    public void add(Teacher teacher) {
        if (teacher.getConfigurations() == null) {
            teacher.setConfigurations(new ArrayList<>());
        }
        dao.add(teacher);
    }

    @Override
    public Teacher update(Teacher teacher) {
        return dao.update(teacher);
    }

    @Override
    public Teacher findByName(String userName) {
        return dao.findByName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Teacher teacher = this.findByName(userName);
        if (teacher == null) {
            throw new UsernameNotFoundException("Teacher " + userName + " is not found");
        }
        return new AuthorizedUser(teacher);
    }
}
