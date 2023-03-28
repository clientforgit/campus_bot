package com.schedule_bot.controllers;


import com.schedule_bot.controllers.TelegramBotController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
@Slf4j
public class UpdateController {
    private final TelegramBotController telegramBotController;

    public UpdateController(TelegramBotController telegramBotController){
        this.telegramBotController = telegramBotController;
    }

    public void processUpdate(Update update) {
        if (update == null) {
            log.error("Received update is null");
            return;
        }

        if (update.hasMessage()) {
            distributeMessageByType(update);
        } else {
            log.error("Received unsupported message type" + update);
        }
    }

    private void distributeMessageByType(Update update) {
        var message = update.getMessage();
        if (message.hasText()) {
            processTextMessage(message);
        } else {
            log.warn("Received unprocessble message" + message);
        }
    }

    private void processTextMessage(Message message){
        var chatId = message.getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("fuck you");
        System.out.println(message.getText());

    }

}
