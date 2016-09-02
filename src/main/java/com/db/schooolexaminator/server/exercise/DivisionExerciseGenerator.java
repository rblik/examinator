package com.db.schooolexaminator.server.exercise;

import com.db.schooolexaminator.model.OperationConstraint;

import java.util.Random;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public class DivisionExerciseGenerator implements ExerciseGenerator {
    OperationConstraint operationConstraint;

    NumberConstraint constraintA;
    NumberConstraint constraintB;
    NumberConstraint constraintAns;
    boolean isDivisionWithoutRemainder;



    Random r = new Random();



    public DivisionExerciseGenerator(OperationConstraint operationConstraint) {
        constraintA = new NumberConstraint(operationConstraint.getMinA(), operationConstraint.getMaxA(), operationConstraint.getExceptAListInteger(), operationConstraint.getSpecialAListInteger());
        constraintB = new NumberConstraint(operationConstraint.getMinB(), operationConstraint.getMaxB(), operationConstraint.getExceptBListInteger(), operationConstraint.getSpecialBListInteger());
        constraintAns = new NumberConstraint(operationConstraint.getMinAnswer(), operationConstraint.getMaxAnswer());
        isDivisionWithoutRemainder = operationConstraint.isDivisionWithoutRemainder();
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

        exercise.setAnswer(a / b);



        return exercise;
    }
}
