package com.db.schooolexaminator.server.exercise;

import com.db.schooolexaminator.model.Constraint;

import java.util.Random;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public class MinusExerciseGenerator implements ExerciseGenerator  {
    Constraint constraint;

    NumberConstraint constraintA;
    NumberConstraint constraintB;
    boolean isAllowedNegativeAnswer;



    Random r = new Random();



    public MinusExerciseGenerator(Constraint constraint) {
        constraintA = new NumberConstraint(constraint.getMinA(), constraint.getMaxA(), constraint.getExcept(), constraint.getSpecial());
        constraintB = new NumberConstraint(constraint.getMinB(), constraint.getMaxB(), constraint.getExcept(), constraint.getSpecial());
        isAllowedNegativeAnswer = constraint.isAllowedNegativeAnswer();
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
