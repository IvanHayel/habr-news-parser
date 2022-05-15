package by.teachmeskills.service.config;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HabrNewsConfig {
    private static final String HABR_NEWS_URL = "https://habr.com/ru/news/";

    @Bean
    public Connection connection() {
        return Jsoup.connect(HABR_NEWS_URL);
    }
}