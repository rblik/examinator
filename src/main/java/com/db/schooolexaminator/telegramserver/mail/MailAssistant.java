package com.db.schooolexaminator.telegramserver.mail;

import com.db.schooolexaminator.telegramserver.statistics.Statistics;

/**
 * Created by JavaSchoolStudent on 02.09.2016.
 */
public interface MailAssistant {
    void sendExamStatistics(String pupilName,String configurationTitle, Statistics statistics);
}
