package com.db.schooolexaminator.telegramserver.telegrambot; /**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
import org.telegram.telegrambots.api.objects.*;

public interface MessageProcessor {
    void processMessage(Message message);
}
