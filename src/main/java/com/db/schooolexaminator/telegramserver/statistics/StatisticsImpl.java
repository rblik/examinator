package com.db.schooolexaminator.telegramserver.statistics;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by JavaSchoolStudent on 02.09.2016.
 */
public class StatisticsImpl implements Statistics {


    @Getter
    private int countExercises;
    @Getter
    private int countCorrectAnswers = 0;
    @Getter
    private int countSkipAnswers = 0;
    @Getter
    private int countIncorrectAnswers = 0;
    @Getter
    private long spentMinutes = 0;

    LocalDateTime startTime;

    public StatisticsImpl(int countExercises) {
        this.countExercises = countExercises;
    }

    @Override
    public void startExam() {
        startTime = LocalDateTime.now();
    }

    @Override
    public void correctAnswer() {
        countCorrectAnswers++;
    }

    @Override
    public void skipAnswer() {
        countSkipAnswers++;
    }

    @Override
    public void incorrectAnswer() {
        countIncorrectAnswers++;
    }

    @Override
    public void finishExam() {
        LocalDateTime finishTime = LocalDateTime.now();
        spentMinutes = ChronoUnit.MINUTES.between(startTime, finishTime);
    }

}
