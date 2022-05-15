package by.teachmeskills.service.controller;

import by.teachmeskills.service.domain.HabrNews;
import by.teachmeskills.service.service.HabrNewsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/habr-news")
@AllArgsConstructor
public class HabrNewsRestController {
    private final HabrNewsService service;

    @GetMapping("/latest")
    public List<HabrNews> getLatestNews() {
        return service.findLatestNews();
    }
}