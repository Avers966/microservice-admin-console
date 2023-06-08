package ru.skillbox.diplom.group35.microservice.admin_console.impl.config;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class SchedulerConfig {

    @Bean
    Scheduler scheduler(List<Trigger> triggers, List<JobDetail> jobDetails, SchedulerFactoryBean factory) throws SchedulerException {
        factory.setWaitForJobsToCompleteOnShutdown(true);
        Scheduler scheduler = factory.getScheduler();
        revalidateJobs(jobDetails, scheduler);
        rescheduleTriggers(triggers, scheduler);
        scheduler.start();
        return scheduler;
    }


    private void rescheduleTriggers(List<Trigger> triggers, Scheduler scheduler) throws SchedulerException {
        for (Trigger trigger : triggers) {
            if (!scheduler.checkExists(trigger.getKey())) {
                scheduler.scheduleJob(trigger);
            } else {
                scheduler.rescheduleJob(trigger.getKey(), trigger);
            }
        }
    }

    private void revalidateJobs(List<JobDetail> jobDetails, Scheduler scheduler) throws SchedulerException {
        List<JobKey> jobKeys = jobDetails.stream().map(JobDetail::getKey).collect(Collectors.toList());
        for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.anyGroup())) {
            if (!jobKeys.contains(jobKey)) {
                scheduler.deleteJob(jobKey);
            }
        }
    }

}
