package com.ilyaproject.newsTelegramBot.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;

@RequiredArgsConstructor
@Component
public class NewsTelegramBot implements SpringLongPollingBot {
    @Value("${telegram.api_key}")
    private String token;
    private final NewsTelegramBotService newsTelegramBotService;
    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return newsTelegramBotService;
    }
}
