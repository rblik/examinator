package com.db.schooolexaminator.server.statistics;

/**
 * Created by JavaSchoolStudent on 02.09.2016.
 */
public interface Statistics {
    void startExam();
    void correctAnswer();
    void skipAnswer();
    void incorrectAnswer();
    void finishExam();

    int getCountExercises();
    int getCountCorrectAnswers();
    int getCountSkipAnswers();
    int getCountIncorrectAnswers();
    long getSpentMinutes();


}
