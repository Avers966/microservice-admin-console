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
public class BirthDayNotificationJobConfig {
    private final JobsInfoConfig jobsInfoConfig;

        @Bean
        JobDetail initJob() {
            Class<? extends QuartzJobBean> jobClass = getClass(jobsInfoConfig.getClassName()) ;
            return JobBuilder
                    .newJob(jobClass)
                    .withIdentity(jobsInfoConfig.getName())
                    .storeDurably()
                    .requestRecovery(true)
                    .build();
    }

    @Bean
    Trigger initTrigger() {
           return TriggerBuilder
                    .newTrigger()
                    .forJob(jobsInfoConfig.getName())
                    .withIdentity(jobsInfoConfig.getName())
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobsInfoConfig.getCron()))
                    .build();
    }

    private Class<? extends QuartzJobBean> getClass(String ClassName) {
        Class<? extends QuartzJobBean> classObject = null;
        try {
            classObject = (Class<? extends QuartzJobBean>)
                    Class.forName(ClassName);
        } catch(ClassNotFoundException e) {
            log.error("createJob(): ClassNotFoundException on job class {} - {}",
                    ClassName, e.getMessage());
        } catch(Exception e) {
            log.error("createJob(): Exception on job class {} - {}",
                    ClassName, e.getMessage());
        }
        return classObject;
    }
}
