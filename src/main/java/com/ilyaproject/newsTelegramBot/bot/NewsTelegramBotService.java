package com.ilyaproject.newsTelegramBot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class NewsTelegramBotService implements LongPollingSingleThreadUpdateConsumer {
    private final TelegramClient telegramClient;
    @Value("${telegram.api_key}")
    private String token;

    public NewsTelegramBotService() {
        this.telegramClient = new OkHttpTelegramClient(token);
    }

    @Override
    public void consume(Update update) {

    }
}
