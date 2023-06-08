package ru.skillbox.diplom.group35.microservice.admin_console.impl.service.scheduler.jobs;

import org.quartz.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.quartz.QuartzJobBean;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.service.scheduler.JobsService;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class BirthDayNotificationJob extends QuartzJobBean {

    private final JobsService jobsService;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        log.info("execute job: \"{}\" at time {}", context.getJobDetail().getKey().getName(), LocalDateTime.now());
        jobsService.callBirthDayNotification();
    }
}