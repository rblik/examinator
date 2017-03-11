package com.db.schooolexaminator.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"address"})
public class Email {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String address;

    public Email(String address) {
        this.address = address;
    }
}
