package com.db.schooolexaminator.server.exercise;

import com.db.schooolexaminator.model.OperationConstraint;

import java.util.Random;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public class MultiplyExerciseGenerator implements ExerciseGenerator {
    OperationConstraint operationConstraint;

    NumberConstraint constraintA;
    NumberConstraint constraintB;
    NumberConstraint constraintAns;



    Random r = new Random();



    public MultiplyExerciseGenerator(OperationConstraint operationConstraint) {
        constraintA = new NumberConstraint(operationConstraint.getMinA(), operationConstraint.getMaxA(), operationConstraint.getExceptListInteger(), operationConstraint.getSpecialListInteger());
        constraintB = new NumberConstraint(operationConstraint.getMinB(), operationConstraint.getMaxB(), operationConstraint.getExceptListInteger(), operationConstraint.getSpecialListInteger());
        constraintAns = new NumberConstraint(operationConstraint.getMinAnswer(), operationConstraint.getMaxAnswer());
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

        exercise.setAnswer(a * b);

        return exercise;
    }
}
