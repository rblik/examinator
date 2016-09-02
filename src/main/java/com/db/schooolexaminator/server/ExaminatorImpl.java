package com.db.schooolexaminator.server;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.Email;
import com.db.schooolexaminator.model.OperationConstraint;
import com.db.schooolexaminator.server.exercise.*;
import com.db.schooolexaminator.server.mail.MailAssistant;
import com.db.schooolexaminator.server.mail.MailAssistantImpl;
import com.db.schooolexaminator.server.mail.MailSender;
import com.db.schooolexaminator.server.picture.PictureManager;
import com.db.schooolexaminator.server.statistics.Statistics;
import com.db.schooolexaminator.server.statistics.StatisticsImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */


public class ExaminatorImpl implements Examinator {

    @Setter @Getter
    private String pupilName;

    @Getter
    private Configuration configuration;

    private PictureManager pictureManager;

    private List<ExerciseGenerator> generators;
    private Exercise currentExercise;
    private boolean isFirstGeneration;

    Statistics statistics;

    @Autowired
    private MailSender mailSender;

    private MailAssistant mailAssistant;

    Random r = new Random();

    public ExaminatorImpl() {}

    private void init() {
        generators = new ArrayList<ExerciseGenerator>();
        for (OperationConstraint c : configuration.getOperationConstraints()) {
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
        pictureManager = new PictureManager("C:\\Users\\JavaSchoolStudent\\Desktop\\SchoolExaminator\\src\\main\\resources\\pictures\\picture.jpg" ,configuration.getFrameRows(), configuration.getFrameCols());
        statistics = new StatisticsImpl(configuration.getFrameCols() * configuration.getFrameRows());
        mailAssistant = new MailAssistantImpl(configuration.getListEmailsString(), mailSender);
        isFirstGeneration = true;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        init();
    }


    @Override
    public boolean answerIsCorrect(int answer) {
        if (answer == currentExercise.getAnswer()) {
            statistics.correctAnswer();
            pictureManager.openPiece();
            currentExercise = null;
            return true;
        } else {
            statistics.incorrectAnswer();
            return false;
        }
    }

    @Override
    public int skipCurrent() {
        int ans = currentExercise.getAnswer();
        currentExercise = null;
        statistics.skipAnswer();
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
        if (isFirstGeneration) {
            statistics.startExam();
            isFirstGeneration = false;
        }
        if (statistics.getCountCorrectAnswers() == statistics.getCountExercises()) {
            statistics.finishExam();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mailAssistant.sendExamStatistics(pupilName, configuration.getTitle(), statistics);
                }
            }).start();
            return null;
        } else {
            ExerciseGenerator exerciseGenerator = generators.get(r.nextInt(generators.size()));
            Exercise exercise = exerciseGenerator.generate();
            currentExercise = exercise;
            return exercise;
        }
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
