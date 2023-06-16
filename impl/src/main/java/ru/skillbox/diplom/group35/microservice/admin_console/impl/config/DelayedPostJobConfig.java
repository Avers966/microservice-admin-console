package ru.skillbox.diplom.group35.microservice.admin_console.impl.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DelayedPostJobConfig {
    private final DelayedPostInfoConfig delayedPostInfoConfig;

    @Bean
    JobDetail initDelayedPostJob() {
        Class<? extends QuartzJobBean> jobClass = getClass(delayedPostInfoConfig.getClassName());
        return JobBuilder
                .newJob(jobClass)
                .withIdentity(delayedPostInfoConfig.getName())
                .storeDurably()
                .requestRecovery(true)
                .build();
    }

    @Bean
    Trigger initDelayedPostTrigger() {
        return TriggerBuilder
                .newTrigger()
                .forJob(delayedPostInfoConfig.getName())
                .withIdentity(delayedPostInfoConfig.getName())
                .withSchedule(CronScheduleBuilder.cronSchedule(delayedPostInfoConfig.getCron()))
                .build();
    }

    private Class<? extends QuartzJobBean> getClass(String ClassName) {
        Class<? extends QuartzJobBean> classObject = null;
        try {
            classObject = (Class<? extends QuartzJobBean>)
                    Class.forName(ClassName);
        } catch (ClassNotFoundException e) {
            log.error("createJob(): ClassNotFoundException on job class {} - {}",
                    ClassName, e.getMessage());
        } catch (Exception e) {
            log.error("createJob(): Exception on job class {} - {}",
                    ClassName, e.getMessage());
        }
        return classObject;
    }
}
