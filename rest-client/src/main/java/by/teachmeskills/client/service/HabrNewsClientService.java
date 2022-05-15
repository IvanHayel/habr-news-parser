package by.teachmeskills.client.service;

import by.teachmeskills.client.domain.HabrNews;

import java.net.URISyntaxException;
import java.util.List;

public interface HabrNewsClientService {
    List<HabrNews> findLatestNews() throws URISyntaxException;
}
