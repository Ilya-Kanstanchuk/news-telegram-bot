package com.ilyaproject.newsTelegramBot.utils;

public interface DataToMessageConverter<T extends DataToObject>{
    String convert(T data);
}
