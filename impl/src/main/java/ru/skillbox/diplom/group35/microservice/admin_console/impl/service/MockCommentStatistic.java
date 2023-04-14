package ru.skillbox.diplom.group35.microservice.admin_console.impl.service;

import org.springframework.stereotype.Component;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.StatisticPerDateDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account.AccountCountPerAge;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account.AccountStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account.AccountStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.comment.CommentStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.comment.CommentStatisticResponseDto;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MockAccountStatistic
 *
 * @author Georgii Vinogradov
 */

@Component
public class MockCommentStatistic {
    private Random random = new Random();

    public CommentStatisticResponseDto getStatistic(CommentStatisticRequestDto requestDto) {
        CommentStatisticResponseDto responseDto = new CommentStatisticResponseDto();
        responseDto.setDate(requestDto.getDate());
        responseDto.setCount(random.nextInt(1000) + 500);
        responseDto.setCountPerMonth(countPerMonthGenerator(requestDto.getFirstMonth(), requestDto.getLastMonth()));
        responseDto.setCountPerHours(countPerHoursGenerator(requestDto.getDate()));
        return responseDto;
    }

    private List<StatisticPerDateDto> countPerMonthGenerator(ZonedDateTime firstMonth, ZonedDateTime lastMonth) {
        List<StatisticPerDateDto> countPerMonths = new ArrayList<>();
        while (firstMonth.isBefore(lastMonth) || firstMonth.isEqual(lastMonth)) {
            countPerMonths.add(new StatisticPerDateDto(firstMonth, random.nextInt(100)+50));
            firstMonth = firstMonth.plusMonths(1);
        }
        return countPerMonths;
    }

    private List<StatisticPerDateDto> countPerHoursGenerator(ZonedDateTime date) {
        List<StatisticPerDateDto> countPerHours = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            ZonedDateTime time = date.withHour(i).withMinute(0);
            Integer count = random.nextInt(10) + 10;
            countPerHours.add(new StatisticPerDateDto(time, count));
        }
        return countPerHours;
    }

}
