package by.teachmeskills.client.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "habr-news")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientCommunicationProperties {
    String url;
    String basePath;
}