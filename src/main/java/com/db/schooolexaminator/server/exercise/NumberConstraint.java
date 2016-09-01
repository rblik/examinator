package com.db.schooolexaminator.server.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */

@Data
public class NumberConstraint {
    Integer minValue;
    Integer maxValue;
    List<Integer> except;
    List<Integer> special;

    public NumberConstraint(Integer minValue, Integer maxValue) {
        initMinMaxValue(minValue, maxValue);
        except = new ArrayList<Integer>();
        special = new ArrayList<Integer>();
    }

    public NumberConstraint(Integer minValue, Integer maxValue, List<Integer> except, List<Integer> special) {
        initMinMaxValue(minValue, maxValue);
        this.except = except;
        this.special = special;
    }

    public void initMinMaxValue(Integer minValue, Integer maxValue) {
        if (minValue == null) {
            this.minValue = Integer.MIN_VALUE;
        } else {
            this.minValue = minValue;
        }
        if (maxValue == null) {
            this.maxValue = Integer.MAX_VALUE;
        } else {
            this.maxValue = maxValue;
        }
    }


    public Integer generateNumber() {
        Random r = new Random();
        int countOperation = 0;
        if ((special== null) || (special.size() == 0)) { //range
            while (countOperation < 1000) {
                Integer value = this.getMinValue() + r.nextInt(this.getMaxValue() - this.getMinValue());
                if ((except != null) && (except.contains(value))) {
                    countOperation++;
                    continue;
                } else {
                    return value;
                }
            }
            throw new RuntimeException("Can't generate number with constraints: " + this);
        } else {
            List<Integer> specials = this.getSpecial();
            Integer ind = r.nextInt(specials.size());
            return specials.get(ind);
        }
    }
}
