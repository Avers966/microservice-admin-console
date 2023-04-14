package ru.skillbox.diplom.group35.microservice.admin_console.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.skillbox.diplom.group35.library.core.annotation.*;

@JwtProvider
@EnableSwagger
@EnableSecurity
@EnableOpenFeign
@EnableBaseRepository
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
