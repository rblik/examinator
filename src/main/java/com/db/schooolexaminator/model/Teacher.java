package com.db.schooolexaminator.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@Entity
@NoArgsConstructor
@Data
@ToString(exclude = {"configurations"})
@Access(AccessType.FIELD)
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(unique = true)
    String userName;
    String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "teacher")
    private List<Configuration> configurations;

    public Teacher(String username, String password, List<Configuration> configurations) {
        this.userName = username;
        this.password = password;
        this.configurations = configurations;
    }

    public Teacher(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
