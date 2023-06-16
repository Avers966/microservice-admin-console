package ru.skillbox.diplom.group35.microservice.admin_console.impl.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "scheduler.delayedpost")
public class DelayedPostInfoConfig {
    private String name;
    private String cron;
    private String className;
}
