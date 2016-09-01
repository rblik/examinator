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
public class SpecificNumber {
    @Id
    @GeneratedValue
    private int id;
    private int value;

    public SpecificNumber(int value) {
        this.value = value;
    }
}