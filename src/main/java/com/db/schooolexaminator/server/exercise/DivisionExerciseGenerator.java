package com.db.schooolexaminator.server.exercise;

import com.db.schooolexaminator.model.Constraint;

import java.util.Random;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public class DivisionExerciseGenerator implements ExerciseGenerator {
    Constraint constraint;

    NumberConstraint constraintA;
    NumberConstraint constraintB;
    NumberConstraint constraintAns;
    boolean isDivisionWithoutRemainder;



    Random r = new Random();



    public DivisionExerciseGenerator(Constraint constraint) {
        constraintA = new NumberConstraint(constraint.getMinA(), constraint.getMaxA(), constraint.getExcept(), constraint.getSpecial());
        constraintB = new NumberConstraint(constraint.getMinB(), constraint.getMaxB(), constraint.getExcept(), constraint.getSpecial());
        constraintAns = new NumberConstraint(constraint.getMinAnswer(), constraint.getMaxAnswer());
        isDivisionWithoutRemainder = constraint.isDivisionWithoutRemainder();
    }

    @Override
    public Exercise generate() {
        Exercise exercise = new Exercise();
        exercise.setOperation(Operation.DIVIDE);

        int a = constraintA.generateNumber();


        if (constraintAns.getMinValue() > constraintA.getMinValue()) {
            constraintA.setMinValue(constraintAns.getMinValue());
        }

        exercise.setA(a);


        if (isDivisionWithoutRemainder) {
            constraintB.setMaxValue(Math.min(constraintB.getMaxValue(), a));
        }
        int b = constraintB.generateNumber();

        exercise.setB(b);

        return exercise;
    }
}
