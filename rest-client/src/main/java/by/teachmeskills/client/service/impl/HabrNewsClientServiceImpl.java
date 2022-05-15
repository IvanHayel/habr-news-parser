package by.teachmeskills.client.service.impl;

import by.teachmeskills.client.config.ClientCommunicationProperties;
import by.teachmeskills.client.domain.HabrNews;
import by.teachmeskills.client.error.handler.HabrNewsErrorHandler;
import by.teachmeskills.client.service.HabrNewsClientService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HabrNewsClientServiceImpl implements HabrNewsClientService {
    final RestTemplate template;
    final ClientCommunicationProperties properties;

    public HabrNewsClientServiceImpl(RestTemplate template, ClientCommunicationProperties properties) {
        template.setErrorHandler(new HabrNewsErrorHandler());
        this.template = template;
        this.properties = properties;
    }

    @Override
    public List<HabrNews> findLatestNews() throws URISyntaxException {
        URI uri = new URI(properties.getUrl() + properties.getBasePath());
        RequestEntity<List<HabrNews>> requestEntity = new RequestEntity<>(HttpMethod.GET, uri);
        ResponseEntity<List<HabrNews>> responseEntity =
                template.exchange(requestEntity, new ParameterizedTypeReference<>() {
                });
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }
        return Collections.emptyList();
    }
}