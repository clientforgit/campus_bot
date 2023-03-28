package com.schedule_bot.controllers;


import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class TelegramBotController extends TelegramWebhookBot {
    @Value("${bot.name}")
    private String username;
    @Value("${bot.token}")
    private String token;

    @Value("${bot.url}")
    private String url;

    @PostConstruct
    public void init() {
        try {
            var setWebhook = SetWebhook.builder().url(url).build();
            this.setWebhook(setWebhook);
        } catch (TelegramApiException e) {
            log.error("Error occurred:" + e);
        }
    }
    @Override
    public String getBotUsername() {
        return username;
    }
    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotPath() {
        return "/update";
    }

}
