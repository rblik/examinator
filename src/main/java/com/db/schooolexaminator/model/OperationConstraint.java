package com.db.schooolexaminator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 31.08.2016.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationConstraint {
    private String sign;
    private int minA;
    private int maxA;
    private int minB;
    private int maxB;
    private int minAnswer;
    private int maxAnswer;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SpecificNumber> except;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SpecificNumber> special;
    private boolean allowedNegativeAnswer;
    private boolean divisionWithoutRemainder;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int constraintId;


    public List<Integer> getExceptListInteger() {
        return getListInteger(except);
    }

    public List<Integer> getSpecialListInteger() {
        return getListInteger(special);
    }

    private static List<Integer> getListInteger(List<SpecificNumber> list) {
        if (list == null) {
            return null;
        }
        List<Integer> ans = new ArrayList<Integer>();
        for (SpecificNumber specificNumber : list) {
            ans.add(specificNumber.getValue());
        }
        return ans;
    }
}