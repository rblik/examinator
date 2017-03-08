package com.db.schooolexaminator.telegramserver;

import com.db.schooolexaminator.telegramserver.telegrambot.ExaminatorMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

import javax.annotation.PostConstruct;

/**
 * Created by JavaSchoolStudent on 02.09.2016.
 */
@Component
public class Strarter {

    @Autowired
    private ExaminatorMessageHandler examinatorMessageHandler;

    @PostConstruct
    public void init() {
        System.out.println("Started bot");
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.db.schooolexaminator.telegramserver");

        //ExaminatorMessageHandler examinatorMessageHandler = context.getBean("examinatorMessageHandler", ExaminatorMessageHandler.class);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(examinatorMessageHandler);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

       // context.close();
    }
}
