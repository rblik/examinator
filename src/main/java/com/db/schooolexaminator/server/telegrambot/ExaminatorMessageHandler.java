package com.db.schooolexaminator.server.telegrambot;

import com.db.schooolexaminator.server.Examinator;
import com.db.schooolexaminator.server.ExaminatorManager;
import com.db.schooolexaminator.server.ExaminatorManagerImpl;
import com.db.schooolexaminator.server.exercise.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendSticker;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Sticker;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardHide;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 31.08.2016.
 */

@Component
public class ExaminatorMessageHandler extends TelegramLongPollingBot implements MessageProcessor {

    public void onUpdateReceived(Update update) {
        final Message message = update.getMessage();
        new Thread(){

            public void run()
            {
                processMessage(message);
            }
        }.start();
    }

    public String getBotUsername() {
        return "telegrambot.Examinator";
    }
    public String getBotToken() {
        return "220527772:AAGPz-VMhMiejjcd6YvEPHwVIpfZZsRHS9E";
    }

    @Autowired
    private ExaminatorManager manager;
    private final String skipButtonName = "I don't know, skip this";


    public void processMessage(Message message) {

        //TODO: remove this
        //manager = new ExaminatorManagerImpl();

        String messageText = message.getText();
        Integer userId = message.getFrom().getId();
        Long chatId = message.getChatId();
        Integer messageId = message.getMessageId();

        if(!manager.hasExaminator(userId) && messageText.equals("/start")){
            sendWelcome(chatId);
        }
        else if(!manager.hasExaminator(userId)) {
            saveConfigAndAskForName(messageText, userId, chatId, messageId);
        }
        else if(!manager.getExaminator(userId).hasPupilName()) {
            saveNameAndStartExam(messageText, userId, chatId, messageId);
        }
        else if(manager.getExaminator(userId).hasCurrentExercise()) {
            checkAnswerAndContinueExam(messageText, userId, chatId, messageId);
        }
        else
        {
            sendTextMessage("Unsupported state",chatId, messageId);
        }
    }

    void sendWelcome(Long chatId) {
        String welcomeMsg = "Hey, let's get acquainted! I'm Math telegrambot.Examinator bot, with me you can test your math knowledge.";

        String askForConfigKey = "Your teacher or parents should have given you exam key. Send it to me, please, to start the test.";
        sendTextMessage(welcomeMsg + "\n \n" +askForConfigKey, chatId);
    }

    void saveConfigAndAskForName(String messageText, Integer userId, Long chatId, Integer messageId) {
        int configurationCode = -1;
        String answerToSend;
        try{
            configurationCode = Integer.parseInt(messageText);
            if(!manager.createExaminator(userId, configurationCode)) {
                //incorrect code
                answerToSend = "I can't find an exam with such code. Please, try again.";
            }
            else {
                //correct code, ask for name
                answerToSend = "Good! First of all, let's sign your test sheet! What's your name and surname?";
            }
        }
        catch (NumberFormatException e) {
            //invalid format of code
            answerToSend = "I expect that you send me an exam code. Once you give me a valid code, we immediately start the test :)";
        }
        sendTextMessage(answerToSend, chatId, messageId);
    }

    void saveNameAndStartExam(String messageText, Integer userId, Long chatId, Integer messageId) {
        //we expect name of pupil so we save it
        manager.getExaminator(userId).setPupilName(messageText);
        String nameSavedMsg = "Okay, I saved your name.";
        String letsStartExamMsg = "Now we can start our exam. I give you an exercise, you give me only an answer (always integer value).";
        sendTextMessage(nameSavedMsg + " \n" + letsStartExamMsg, chatId, messageId);

        //start exam, give first exercise
        sendNewExerciseOrFinishExam(chatId, manager.getExaminator(userId));
    }

    void sendNewExerciseOrFinishExam(Long chatId, Examinator examinator){
        Exercise newExercise = examinator.generateNextExercise();
        if(newExercise != null) {
            sendTextMessage(newExercise.toStringWithoutResult(), chatId, null, true);
        }
        else {
            sendTextMessage("Well done! Exam is finished! To start another exam, send me exam key. You can ask your parents for it.", chatId);
            sendSticker(StickerTypes.FINISHED_EXAM, chatId);
        }
    }

    void checkAnswerAndContinueExam(String messageText, Integer userId, Long chatId, Integer messageId)
    {
        if(messageText.equals(skipButtonName))
        {
            int correctAnswer = manager.getExaminator(userId).skipCurrent();
            sendSticker(StickerTypes.SKIPPED_QUESTION, chatId);
            sendTextMessage("Correct answer was: " + correctAnswer, chatId, messageId);
            //sendImageInResponseTo(manager.getExaminator(userId).getImage(), messageId, chatId);
            sendNewExerciseOrFinishExam(chatId, manager.getExaminator(userId));
            return;
        }
        //we expect that we already gave an expression to pupil and now receive and answer
        int receivedAnswer = -1;
        String answerToSend;
        String sticker;
        try{
            receivedAnswer = Integer.parseInt(messageText);
            if(!manager.getExaminator(userId).answerIsCorrect(receivedAnswer)) {
                //incorrect answer
                answerToSend = "Wrong, try again. Try again";
                sendSticker(StickerTypes.WRONG_ANSWER, chatId);
                sendTextMessage(answerToSend, chatId, messageId, true);
            }
            else {
                //correct code, ask for name
                sendSticker(StickerTypes.CORRECT_ANSWER, chatId);
                sendImageInResponseTo(manager.getExaminator(userId).getImage(), messageId, chatId);
                sendNewExerciseOrFinishExam(chatId, manager.getExaminator(userId));
            }
        }
        catch (NumberFormatException e) {
            //invalid format of code
            answerToSend = "Try again: answer should be always integer!";
            sendTextMessage(answerToSend, chatId, messageId);
        }
    }

    private void sendTextMessage(String text, Long chatId)
    {
        sendTextMessage(text, chatId, null);
    }
    private void sendTextMessage(String text, Long chatId, Integer messageId)
    {
        sendTextMessage(text, chatId, messageId, false);
    }
    private void sendTextMessage(String text, Long chatId, Integer messageId, boolean showButtons)
    {
        SendMessage newMessage = new SendMessage();
        newMessage.setText(text);
        newMessage.setChatId(chatId.toString());
        if(messageId != null) {
            newMessage.setReplyToMessageId(messageId);
        }
        newMessage.setReplyMarkup(showButtons  ? replyKeyboardMarkup() : new ReplyKeyboardHide());

        try {
            sendMessage(newMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private  void sendImageInResponseTo(String imageName, Integer messageId, Long chatId)
    {
        //send photo
        SendPhoto sendPhotoRequest = new SendPhoto();
        File file = new File(imageName);
        sendPhotoRequest.setNewPhoto(file);
        sendPhotoRequest.setReplyToMessageId(messageId);
        sendPhotoRequest.setChatId(chatId.toString());
        try {
            sendPhoto(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private ReplyKeyboardMarkup replyKeyboardMarkup()
    {
        //create button
        KeyboardButton button = new KeyboardButton();
        button.setText(skipButtonName);

        //create row & put there a button
        KeyboardRow row = new KeyboardRow();
        row.add(button);

        //create keyboard and add row there
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        keyboard.add(row);

        //create markup and put keyboard there
        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setOneTimeKeyboad(true);
        replyMarkup.setKeyboard(keyboard);

        replyMarkup.setResizeKeyboard(true);
        return replyMarkup;
    }

    private void sendSticker(StickerTypes stickerType, Long chatId)
    {
        SendSticker sticker = new SendSticker();
        sticker.setChatId(chatId.toString());
        Sticker newSticker = new Sticker();
        sticker.setSticker(stickerType.toString());
        try {
            sendSticker(sticker);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
