package by.teachmeskills.client.config;

import by.teachmeskills.client.domain.HabrNews;
import by.teachmeskills.client.service.HabrNewsClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@Slf4j
public class ClientConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner commandLineRunner(HabrNewsClientService service) {
        return args -> {
            log.info("Requesting Habr latest news...");
            List<HabrNews> latestNews = service.findLatestNews();
            latestNews.forEach(news -> log.info(news.toString()));
        };
    }
}