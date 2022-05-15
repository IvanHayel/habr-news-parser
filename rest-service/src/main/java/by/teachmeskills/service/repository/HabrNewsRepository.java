package by.teachmeskills.service.repository;

import by.teachmeskills.service.domain.HabrNews;

import java.util.List;

public interface HabrNewsRepository {
    List<HabrNews> findLatestNews();
}
