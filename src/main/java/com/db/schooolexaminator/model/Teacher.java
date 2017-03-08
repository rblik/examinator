package com.db.schooolexaminator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@Entity
@NoArgsConstructor
@Getter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int userId;

    String userName;
    String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Configuration> configurations;

    public Teacher(String username, String password, List<Configuration> configurations) {
        this.userName = username;
        this.password = password;
        this.configurations = configurations;
    }
}
