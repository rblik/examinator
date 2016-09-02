package com.db.schooolexaminator.telegramserver;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.telegramserver.exercise.Exercise;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public interface Examinator {

    boolean answerIsCorrect(int answer);

    int skipCurrent();

    Exercise getCurrentExercise();
    boolean hasCurrentExercise();
    Exercise generateNextExercise();

    boolean hasPupilName();

    String getPupilName();
    void setPupilName(String name);

    String getImage();

    void setConfiguration(Configuration c);
    void setPupilId(int pupilId);
}
