package com.db.schooolexaminator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by JavaSchoolStudent on 31.08.2016.
 */

@Data
@AllArgsConstructor
public class Configuration {
    private String title;
    private List<String> emails;
    int columns;
    int rows;

 /*  private ExerciseGenerator exerciseGenerator;
*/
    List<Constraint> constraints;

/*
    generateConf();*/
}
