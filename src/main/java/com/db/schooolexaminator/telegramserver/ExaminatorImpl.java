package com.db.schooolexaminator.telegramserver;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.OperationConstraint;
import com.db.schooolexaminator.telegramserver.exercise.*;
import com.db.schooolexaminator.telegramserver.mail.MailAssistant;
import com.db.schooolexaminator.telegramserver.mail.MailAssistantImpl;
import com.db.schooolexaminator.telegramserver.mail.MailSender;
import com.db.schooolexaminator.telegramserver.picture.PictureAssistant;
import com.db.schooolexaminator.telegramserver.picture.PictureAssistantImpl;
import com.db.schooolexaminator.telegramserver.picture.PictureManager;
import com.db.schooolexaminator.telegramserver.statistics.Statistics;
import com.db.schooolexaminator.telegramserver.statistics.StatisticsImpl;
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


    private List<ExerciseGenerator> generators;
    private Exercise currentExercise;
    private boolean isFirstGeneration;

    Statistics statistics;

    @Autowired
    private MailSender mailSender;
    private MailAssistant mailAssistant;


    @Autowired
    private PictureManager pictureManager;
    private PictureAssistant pictureAssistant;


    Integer pupilId;
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
        pictureAssistant = new PictureAssistantImpl(pictureManager, pictureManager.defaultPictureFileName(), pupilId, configuration.getFrameRows(), configuration.getFrameCols());
        statistics = new StatisticsImpl(configuration.getFrameCols() * configuration.getFrameRows());
        mailAssistant = new MailAssistantImpl(configuration.getListEmailsString(), mailSender);
        isFirstGeneration = true;
    }

    public void setPupilId(int pupilId) {
        this.pupilId = pupilId;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        init();
    }


    @Override
    public boolean answerIsCorrect(int answer) {
        if (answer == currentExercise.getAnswer()) {
            statistics.correctAnswer();
            pictureAssistant.openPiece();
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
        return pictureAssistant.getImageFileName();
    }

}
