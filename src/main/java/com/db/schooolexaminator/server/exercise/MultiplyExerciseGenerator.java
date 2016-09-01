package com.db.schooolexaminator.server.exercise;

import com.db.schooolexaminator.model.Constraint;

import java.util.Random;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public class MultiplyExerciseGenerator implements ExerciseGenerator {
    Constraint constraint;

    NumberConstraint constraintA;
    NumberConstraint constraintB;
    NumberConstraint constraintAns;



    Random r = new Random();



    public MultiplyExerciseGenerator(Constraint constraint) {
        constraintA = new NumberConstraint(constraint.getMinA(), constraint.getMaxA(), constraint.getExcept(), constraint.getSpecial());
        constraintB = new NumberConstraint(constraint.getMinB(), constraint.getMaxB(), constraint.getExcept(), constraint.getSpecial());
        constraintAns = new NumberConstraint(constraint.getMinAnswer(), constraint.getMaxAnswer());
    }

    @Override
    public Exercise generate() {
        Exercise exercise = new Exercise();
        exercise.setOperation(Operation.MULTIPLY);

        if (constraintA.getMaxValue() > constraintAns.getMaxValue()) {
            constraintA.setMaxValue(constraintAns.getMaxValue());
        }

        int a = constraintA.generateNumber();
        exercise.setA(a);

        if ((a * constraintB.getMaxValue()) > constraintAns.getMaxValue()) {
            constraintB.setMaxValue(constraintAns.getMaxValue() / a);
        }

        if ((a * constraintB.getMinValue()) < constraintAns.getMinValue()) {
            constraintB.setMinValue(constraintAns.getMinValue() / a);
        }

        int b = constraintB.generateNumber();
        exercise.setB(b);

        return exercise;
    }
}
