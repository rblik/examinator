package com.db.schooolexaminator.server.mail;

import com.db.schooolexaminator.server.statistics.Statistics;

import java.util.List;

/**
 * Created by JavaSchoolStudent on 02.09.2016.
 */
public class MailAssistantImpl implements MailAssistant {

    MailSender mailSender;
    List<String> emails;

    public MailAssistantImpl(List<String> emails, MailSender mailSender) {
        this.emails = emails;
        this.mailSender = mailSender;
    }

    @Override
    public void sendExamStatistics(String name, String configurationTitle, Statistics statistics) {
        String subject = name + " " + configurationTitle + " results";
        String body = name + " finished " + configurationTitle + " with results: \n"
                + "Number of correct answers: " + statistics.getCountCorrectAnswers() + ";\n"
                + "Number of incorrect answers: " + statistics.getCountIncorrectAnswers() + ";\n"
                + "Number of skip questions: " + statistics.getCountSkipAnswers() + ";\n"
                + "Time spent: " + statistics.getSpentMinutes() + " minutes.";
        for (String email : emails) {
            mailSender.sendEmail(email, subject, body);
        }
    }

}
