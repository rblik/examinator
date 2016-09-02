package com.db.schooolexaminator.telegramserver;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public interface ExaminatorManager {

    boolean hasExaminator(int pupilId);
    boolean createExaminator(int pupilId, int configurationId);
    Examinator getExaminator(int pupilId);
    void removeExaminator(int pupilId);

}
