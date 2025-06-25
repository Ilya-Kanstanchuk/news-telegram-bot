package com.ilyaproject.newsTelegramBot.bot;

import com.fasterxml.jackson.databind.JsonNode;
import com.ilyaproject.newsTelegramBot.weather.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class NewsTelegramBotService implements LongPollingSingleThreadUpdateConsumer {
    private final TelegramClient telegramClient;
    @Autowired
    private CityService cityService;
    private final String welcomeMessage = "Hi, I'm Morning Bot and my goal is to make your morning more informative. " +
            "I'm going to send you latest news articles, currency information and weather in your city. To continue " +
            "please enter your city name: ";
    private final Map<Long, BotState> userStates = new ConcurrentHashMap<>();

    // Define states for your bot
    private enum BotState {
        NONE,
        AWAITING_CITY,
    }

    public NewsTelegramBotService(@Value("${telegram.api_key}") String token) {
        this.telegramClient = new OkHttpTelegramClient(token);
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage()){
            Long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();
            BotState state = userStates.getOrDefault(chatId, BotState.NONE);
            switch (state){
                case NONE:
                    if (messageText.equals("/start")){
                        printMessage(chatId, welcomeMessage);
                        userStates.put(chatId, BotState.AWAITING_CITY);
                    }else{
                        printMessage(chatId, "Please enter /start to begin");
                    }
                    break;
                case AWAITING_CITY:
                    try {
                        List<Double> coordinates = cityService.getCoordinates(messageText);
                        if (coordinates != null){
                            printMessage(chatId, "Your city is " + messageText);
                            printMessage(chatId, "We are done with initialization!");
                            printMessage(chatId, "Enter /start to begin");
                            userStates.remove(chatId);
                        }else{
                            printMessage(chatId, "Something went wrong, please enter yor city again");
                        }
                    }catch (Exception e){
                        printMessage(chatId, "Something went wrong, please enter yor city again");
                        log.error("Failed to get coordinates for " + messageText + " " + e.getMessage());
                    }
                    break;
            }
        }
    }

    private void printMessage(Long chatId, String messageText){
        try {
            SendMessage message = SendMessage.builder()
                    .text(messageText)
                    .chatId(chatId)
                    .build();
            telegramClient.execute(message);
        }catch (Exception e){
            log.error("Failed to write message " + messageText + " " + e.getMessage());
        }
    }
}
