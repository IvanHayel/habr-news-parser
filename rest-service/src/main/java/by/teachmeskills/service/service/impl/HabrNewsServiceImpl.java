package by.teachmeskills.service.service.impl;

import by.teachmeskills.service.domain.HabrNews;
import by.teachmeskills.service.repository.HabrNewsRepository;
import by.teachmeskills.service.service.HabrNewsService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HabrNewsServiceImpl implements HabrNewsService {
    final HabrNewsRepository repository;

    @Override
    public List<HabrNews> findLatestNews() {
        return repository.findLatestNews();
    }
}