package com.db.schooolexaminator.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"address"})
@Access(AccessType.FIELD)
public class Email {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String address;

    public Email(String address) {
        this.address = address;
    }
}
