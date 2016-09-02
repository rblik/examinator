package com.db.schooolexaminator.telegramserver.exercise;

import com.db.schooolexaminator.model.OperationConstraint;

import java.util.Random;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public class MinusExerciseGenerator implements ExerciseGenerator  {
    OperationConstraint operationConstraint;

    NumberConstraint constraintA;
    NumberConstraint constraintB;
    boolean isAllowedNegativeAnswer;



    Random r = new Random();



    public MinusExerciseGenerator(OperationConstraint operationConstraint) {
        constraintA = new NumberConstraint(operationConstraint.getMinA(), operationConstraint.getMaxA(), operationConstraint.getExceptAListInteger(), operationConstraint.getSpecialAListInteger());
        constraintB = new NumberConstraint(operationConstraint.getMinB(), operationConstraint.getMaxB(), operationConstraint.getExceptBListInteger(), operationConstraint.getSpecialBListInteger());
        isAllowedNegativeAnswer = operationConstraint.isAllowedNegativeAnswer();
    }

    @Override
    public Exercise generate() {
        Exercise exercise = new Exercise();
        exercise.setOperation(Operation.MINUS);

        int a = constraintA.generateNumber();
        exercise.setA(a);


        if (!isAllowedNegativeAnswer) {
            constraintB.setMaxValue(Math.min(constraintB.getMaxValue(), a));
        }

        int b = constraintB.generateNumber();
        exercise.setB(b);

        exercise.setAnswer(a - b);


        return exercise;
    }
}
