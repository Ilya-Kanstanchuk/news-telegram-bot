package com.ilyaproject.newsTelegramBot.bot;

import com.ilyaproject.newsTelegramBot.city.controller.CityInitialization;
import com.ilyaproject.newsTelegramBot.currency.controller.CurrencyController;
import com.ilyaproject.newsTelegramBot.model.City;
import com.ilyaproject.newsTelegramBot.model.User;
import com.ilyaproject.newsTelegramBot.news.controller.NewsController;
import com.ilyaproject.newsTelegramBot.repository.UserRepository;
import com.ilyaproject.newsTelegramBot.user.controller.UserInitialization;
import com.ilyaproject.newsTelegramBot.weather.controller.WeatherController;
import com.ilyaproject.newsTelegramBot.weather.service.CoordinatesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
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
    private UserRepository repository;
    @Autowired
    private CityInitialization cityInitialization;
    @Autowired
    private UserInitialization initialization;
    @Autowired
    private CoordinatesService coordinatesService;
    @Autowired
    private CurrencyController currencyController;
    @Autowired
    private WeatherController weatherController;
    @Autowired
    private NewsController newsController;
    private final String welcomeMessage = "Hi, I'm Morning Bot and my goal is to make your morning more informative. " +
            "I'm going to send you latest news articles, currency information and weather in your city. To continue " +
            "please enter your city name: ";
    private final Map<Long, BotState> userStates = new ConcurrentHashMap<>();

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
            String name = update.getMessage().getFrom().getFirstName();
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
                        List<Double> coordinates = coordinatesService.getCoordinates(messageText);
                        if (coordinates != null){
                            City city = cityInitialization.cityStart(messageText, coordinates.get(0), coordinates.get(1));
                            if (city == null){
                                throw new RuntimeException("City object is null");
                            }
                            initialization.userStart(chatId, name, city);
                            printMessage(chatId, "You where successfully register/update your information!");
                            printMessage(chatId, "Enter /start to begin");
                            userStates.remove(chatId);
                        }else{
                            printMessage(chatId, "Something went wrong, please enter yor city again");
                        }
                    }catch (Exception e){
                        printMessage(chatId, "Something went wrong, please enter yor city again");
                        log.error("Failed to get coordinates for " + messageText + " " + e);
                    }
                    break;
            }
        }
    }

    public void printMessage(Long chatId, String messageText){
        try {
            SendMessage message = SendMessage.builder()
                    .text(messageText)
                    .chatId(chatId)
                    .build();
            telegramClient.execute(message);
        }catch (Exception e){
            log.error("Failed to write message " + messageText + " " + e);
        }
    }
}
