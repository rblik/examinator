package com.db.schooolexaminator.server.mail;

import com.db.schooolexaminator.server.statistics.Statistics;

/**
 * Created by JavaSchoolStudent on 02.09.2016.
 */
public interface MailAssistant {
    void sendExamStatistics(String pupilName,String configurationTitle, Statistics statistics);
}
