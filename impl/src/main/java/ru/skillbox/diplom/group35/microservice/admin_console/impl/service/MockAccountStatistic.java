package ru.skillbox.diplom.group35.microservice.admin_console.impl.service;

import org.springframework.stereotype.Component;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.StatisticPerDateDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account.AccountCountPerAge;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account.AccountStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account.AccountStatisticResponseDto;

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
public class MockAccountStatistic {
    public AccountStatisticResponseDto getStatistic(AccountStatisticRequestDto statisticRequestDto) {
        Random random = new Random();
        AccountStatisticResponseDto responseDto = new AccountStatisticResponseDto();
        responseDto.setDate(statisticRequestDto.getDate());
        responseDto.setCount(random.nextInt(1000));
        responseDto.setMaleCount(random.nextInt(1000));
        responseDto.setFemaleCount(random.nextInt(1000));
        responseDto.setBlankGenderCount(random.nextInt(1000));
        responseDto.setCountPerAge(AccountCountPerAgeGenerator());
        responseDto.setCountPerMonth(AccountCountPerMonthGenerator(statisticRequestDto.getFirstMonth(), statisticRequestDto.getLastMonth()));
        return responseDto;
    }

    private List<StatisticPerDateDto> AccountCountPerMonthGenerator(ZonedDateTime firstMonth, ZonedDateTime lastMonth) {
        Random random = new Random();
        List<StatisticPerDateDto> countPerMonths = new ArrayList<>();
        while (firstMonth.isBefore(lastMonth) || firstMonth.isEqual(lastMonth)) {
            countPerMonths.add(new StatisticPerDateDto(firstMonth, random.nextInt(100)+50));
            firstMonth = firstMonth.plusMonths(1);
        }
        return countPerMonths;
    }

    private List<AccountCountPerAge> AccountCountPerAgeGenerator() {
        Random random = new Random();
        List<AccountCountPerAge> countPerAge = new ArrayList<>();
        for (int i = 10; i < 90; i++) {
            Integer count = random.nextInt(100);
            if (count > 50) {
                countPerAge.add(new AccountCountPerAge(i, count));
            }
        }
        countPerAge.add(new AccountCountPerAge(null, random.nextInt(50)+50));
        return countPerAge;
    }
}
