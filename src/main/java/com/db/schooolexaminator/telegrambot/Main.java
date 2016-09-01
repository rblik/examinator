package telegrambot;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

/**
 * Created by JavaSchoolStudent on 31.08.2016.
 */
public class Main {
    public static void main(String[] args) {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new ExaminatorMessageHandler());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
