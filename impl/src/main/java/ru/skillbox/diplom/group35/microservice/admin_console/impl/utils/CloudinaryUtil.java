package ru.skillbox.diplom.group35.microservice.admin_console.impl.utils;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.config.CloudinaryConfig;

/**
 * CloudinaryConfig
 *
 * @author Georgii Vinogradov
 */

@Configuration
@RequiredArgsConstructor
public class CloudinaryUtil {

    private final CloudinaryConfig cloudinaryConfig;

    @Bean
    public Cloudinary getConfig() {
        return new Cloudinary(cloudinaryConfig.getConfig());
    }
}
