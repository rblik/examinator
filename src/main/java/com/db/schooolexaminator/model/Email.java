package com.db.schooolexaminator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@Data
@Entity
@NoArgsConstructor
public class Email {
    @Id @GeneratedValue
    private int id;
    private String address;

    public Email(String address) {
        this.address = address;
    }
}
