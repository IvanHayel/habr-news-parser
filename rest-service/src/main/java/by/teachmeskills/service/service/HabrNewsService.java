package by.teachmeskills.service.service;

import by.teachmeskills.service.domain.HabrNews;

import java.util.List;

public interface HabrNewsService {
    List<HabrNews> findLatestNews();
}