package ru.skillbox.diplom.group35.microservice.admin_console.impl.utils;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class DateTimeUtils {
    public ZonedDateTime startDay(ZonedDateTime dateTime) {
        return dateTime.truncatedTo(ChronoUnit.DAYS);
    }

    public ZonedDateTime endDay(ZonedDateTime dateTime) {
        return dateTime.truncatedTo(ChronoUnit.DAYS).plusDays(1).minusSeconds(1);
    }

    public ZonedDateTime startMonth(ZonedDateTime dateTime) {
        return dateTime.withDayOfMonth(1).truncatedTo(ChronoUnit.DAYS);
    }

    public ZonedDateTime endMonth(ZonedDateTime dateTime) {
        return dateTime.withDayOfMonth(dateTime.toLocalDate().lengthOfMonth())
                .truncatedTo(ChronoUnit.DAYS).plusDays(1).minusSeconds(1);
    }

}
