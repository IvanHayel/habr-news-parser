package by.teachmeskills.service.repository.impl;

import by.teachmeskills.service.domain.HabrNews;
import by.teachmeskills.service.exception.HabrDateTimeFormatException;
import by.teachmeskills.service.repository.HabrNewsRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HabrNewsRepositoryImpl implements HabrNewsRepository {
    static final String HABR_URL = "https://habr.com";
    static final String ARTICLE_CLASS = "tm-article-snippet";
    static final String ARTICLE_TITLE_CLASS = "tm-article-snippet__title-link";
    static final String TIME_TAG = "time";
    static final String DATETIME_ATTRIBUTE = "datetime";

    final Connection HABR_CONNECTION;

    @Override
    public List<HabrNews> findLatestNews() {
        List<HabrNews> latestNews = new ArrayList<>();
        try {
            Document document = HABR_CONNECTION.get();
            Elements articles = document.getElementsByClass(ARTICLE_CLASS);
            articles.forEach(article -> {
                HabrNews news = parseHabrArticle(article);
                latestNews.add(news);
            });
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return latestNews;
    }

    private HabrNews parseHabrArticle(Element article) {
        LocalDateTime articleDateTime = getArticleDateTime(article);
        Element articleTitle = article.getElementsByClass(ARTICLE_TITLE_CLASS).first();
        String link = getArticleLink(articleTitle);
        String articleTitleText = Objects.requireNonNull(articleTitle).text();
        HabrNews news = new HabrNews();
        news.setDate(articleDateTime);
        news.setTitle(articleTitleText);
        news.setLink(link);
        return news;
    }

    private LocalDateTime getArticleDateTime(Element article) {
        Element articleTime = article.getElementsByTag(TIME_TAG).first();
        String dateTime = Objects.requireNonNull(articleTime).attr(DATETIME_ATTRIBUTE);
        return parseHabrDateTime(dateTime);
    }

    private LocalDateTime parseHabrDateTime(String dateTime) throws HabrDateTimeFormatException {
        Pattern pattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})T(\\d{2}:\\d{2}:\\d{2})");
        Matcher matcher = pattern.matcher(dateTime);
        if (matcher.find()) {
            return LocalDateTime.parse(matcher.group());
        } else {
            throw new HabrDateTimeFormatException();
        }
    }

    private String getArticleLink(Element articleTitle) {
        String articleShortLink = Objects.requireNonNull(articleTitle).attr("href");
        return HABR_URL.concat(articleShortLink);
    }
}