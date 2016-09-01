package com.db.schooolexaminator.server;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.Constraint;
import com.db.schooolexaminator.server.exercise.*;
import com.db.schooolexaminator.server.picture.PictureManager;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */


public class ExaminatorImpl implements Examinator {

    @Setter @Getter
    private String pupilName;

    @Setter @Getter
    private Configuration configuration;

    private PictureManager pictureManager;


    private List<ExerciseGenerator> generators;

    Exercise currentExercise;

    private int incorrectAnswer = 0;
    private int skipQuestions = 0;
    private int correctAnswers = 0;


    public ExaminatorImpl() {}

    public void init() {
        generators = new ArrayList<ExerciseGenerator>();
        for (Constraint c : configuration.getConstraints()) {
            if (c.getSign().equals("+")) {
                generators.add(new PlusExerciseGenerator(c));
            }
            if (c.getSign().equals("-")) {
                generators.add(new MinusExerciseGenerator(c));
            }
            if (c.getSign().equals("*")) {
                generators.add(new MultiplyExerciseGenerator(c));
            }
            if (c.getSign().equals("/")) {
                generators.add(new DivisionExerciseGenerator(c));
            }
        }

        pictureManager = new PictureManager("C:\\Users\\JavaSchoolStudent\\Desktop\\SchoolExaminator\\src\\main\\resources\\pictures\\picture.jpg" ,configuration.getRows(), configuration.getColumns());
    }


    @Override
    public boolean answerIsCorrect(int answer) {
        if (answer == currentExercise.getAnswer()) {
            correctAnswers++;
            pictureManager.openPiece();
            currentExercise = null;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int skipCurrent() {
        int ans = currentExercise.getAnswer();
        currentExercise = null;
        skipQuestions++;
        return ans;
    }

    @Override
    public Exercise getCurrentExercise() {
        return currentExercise;
    }

    @Override
    public boolean hasCurrentExercise() {
        return currentExercise != null;
    }

    @Override
    public Exercise generateNextExercise() {
        Random r = new Random();
        ExerciseGenerator exerciseGenerator = generators.get(r.nextInt(generators.size()));
        Exercise exercise = exerciseGenerator.generate();
        currentExercise = exercise;
        return exercise;
    }

    @Override
    public boolean hasPupilName() {
        System.out.println(pupilName);
        return pupilName != null;
    }

    @Override
    public String getImage() {
        return pictureManager.getImage();
    }

}
