package com.ilyaproject.newsTelegramBot.utils;

import java.util.List;

public interface DataToMessageConverter<T extends DataToObject>{
    String convert(T data);
}
