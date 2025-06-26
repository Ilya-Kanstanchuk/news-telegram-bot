package com.ilyaproject.newsTelegramBot.schedule;

import com.ilyaproject.newsTelegramBot.bot.NewsTelegramBotService;
import com.ilyaproject.newsTelegramBot.city.controller.CityInitialization;
import com.ilyaproject.newsTelegramBot.currency.controller.CurrencyController;
import com.ilyaproject.newsTelegramBot.model.User;
import com.ilyaproject.newsTelegramBot.news.controller.NewsController;
import com.ilyaproject.newsTelegramBot.repository.UserRepository;
import com.ilyaproject.newsTelegramBot.user.controller.UserInitialization;
import com.ilyaproject.newsTelegramBot.weather.controller.WeatherController;
import com.ilyaproject.newsTelegramBot.weather.service.CoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MorningScheduledMessage {

    @Autowired
    private UserRepository repository;
    @Autowired
    private CurrencyController currencyController;
    @Autowired
    private WeatherController weatherController;
    @Autowired
    private NewsController newsController;
    @Autowired
    private NewsTelegramBotService service;
    @Scheduled(cron = "0 0 8 * * *", zone = "Europe/Warsaw")
    public void sendMorningMessageToTheUsers(){
        List<User> users = repository.findAll();
        for (User user: users){
          sendMessageToUser(user);
        }
    }

    @Async
    private void sendMessageToUser(User user){
        service.printMessage(user.getChatId(), weatherController.getForecast(user.getCity().getLatitude(), user.getCity().getLongitude(), user.getCity().getName()));
        service.printMessage(user.getChatId(), newsController.getNews());
        service.printMessage(user.getChatId(), currencyController.getCurrencies());
    }
}
