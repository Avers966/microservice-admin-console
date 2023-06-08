package ru.skillbox.diplom.group35.microservice.admin_console.impl.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "scheduler")
public class JobsInfoConfig {
    private String name;
    private String cron;
    private String className;
}
