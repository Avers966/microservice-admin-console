package ru.skillbox.diplom.group35.microservice.admin_console.impl.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * CloudinarySecrets
 *
 * @author Georgii Vinogradov
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "cloudinary")
public class CloudinaryConfig {
    private Map<String, String> config;
}
