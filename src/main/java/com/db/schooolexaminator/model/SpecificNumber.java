package com.db.schooolexaminator.model;

import lombok.Data;
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
public class SpecificNumber {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private int id;
    private int value;

    public SpecificNumber(int value) {
        this.value = value;
    }
    public SpecificNumber(String value) {
        this.value =  Integer.valueOf(value);
    }
}
