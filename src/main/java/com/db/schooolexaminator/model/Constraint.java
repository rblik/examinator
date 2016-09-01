package com.db.schooolexaminator.model;

import lombok.Data;

import java.util.List;

/**
 * Created by JavaSchoolStudent on 31.08.2016.
 */


@Data
public class Constraint {
    private String sign;
    private int minA;
    private int maxA;
    private int minB;
    private int maxB;
    private int minAnswer;
    private int maxAnswer;
    private List<Integer> except;
    private List<Integer> special;
    private boolean allowedNegativeAnswer;
    private boolean divisionWithoutRemainder;



}
