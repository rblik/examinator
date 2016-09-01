package com.db.schooolexaminator.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 31.08.2016.
 */
@Entity
public class Constraint {
    private String sign;
    private int minA;
    private int maxA;
    private int minB;
    private int maxB;
    private int minAnswer;
    private int maxAnswer;
    @OneToMany(cascade = CascadeType.ALL)
    private List<SpecificNumber> except;
    @OneToMany(cascade = CascadeType.ALL)
    private List<SpecificNumber> special;
    private boolean allowedNegativeAnswer;
    private boolean divisionWithoutRemainder;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int constraintId;
}
