package com.db.schooolexaminator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Integer> exceptA;

//    @Transient
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Integer> exceptB;
//    @Transient
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Integer> specialA;

//    @Transient

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Integer> specialB;
    private boolean allowedNegativeAnswer;
    private boolean divisionWithoutRemainder;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int constraintId;


    public List<Integer> getExceptAListInteger() {
        return getListInteger(exceptA);
    }

    public List<Integer> getSpecialAListInteger() {
        return getListInteger(specialA);
    }

    public List<Integer> getExceptBListInteger() {
        return getListInteger(exceptB);
    }

    public List<Integer> getSpecialBListInteger() {
        return getListInteger(specialB);
    }

    private static List<Integer> getListInteger(Set<Integer> list) {
        if (list == null) {
            return null;
        }
        List<Integer> ans = new ArrayList<Integer>();
        for (Integer specificNumber : list) {
            ans.add(specificNumber);
        }
        return ans;
    }
}
