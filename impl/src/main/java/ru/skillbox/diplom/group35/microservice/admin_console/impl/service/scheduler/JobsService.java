package ru.skillbox.diplom.group35.microservice.admin_console.impl.service.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.library.core.security.config.TechnicalUserConfig;
import ru.skillbox.diplom.group35.microservice.account.api.client.AccountFeignClient;
import ru.skillbox.diplom.group35.microservice.post.resource.client.PostFeignClient;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class JobsService {
    private final AccountFeignClient accountFeignClient;
    private final PostFeignClient postFeignClient;
    private final TechnicalUserConfig technicalUserConfig;

    public void callBirthDayNotification() {
        try {
            ResponseEntity response = technicalUserConfig
                    .executeByTechnicalUser(() -> accountFeignClient.sendBirthdayNotification());
        } catch (Exception e) {
            log.error("Job execution return an error: {}", e.getMessage());
            return;
        }
        log.info("Job execution success");
    }

    public void callDelayJob() {
        try {
            ResponseEntity response = technicalUserConfig
                    .executeByTechnicalUser(() -> postFeignClient.getDelayedPost());
        } catch (Exception e) {
            log.error("Job execution return an error: {}", e.getMessage());
            return;
        }
        log.info("Job execution success");
    }



}
