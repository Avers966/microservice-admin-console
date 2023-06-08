package ru.skillbox.diplom.group35.microservice.admin_console.impl.service.statistic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.library.core.dto.statistic.StatisticPerDateDto;
import ru.skillbox.diplom.group35.microservice.account.api.client.AccountFeignClient;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountCountPerAge;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.MonthStatisticSearchDto;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account.AccountMonthStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account.AccountMonthStatistic_;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account.AccountStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account.CountPerAge;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.account.AccountMonthStatisticMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.account.AccountStatisticMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.account.CountPerAgeMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.repository.account.AccountMonthStatisticRepository;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.repository.account.AccountStatisticRepository;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.repository.account.CountPerAgeRepository;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.utils.DateTimeUtils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.skillbox.diplom.group35.library.core.utils.SpecificationUtil.*;

@Service
@RequiredArgsConstructor
public class AccountStatisticService {

    private final CountPerAgeRepository countPerAgeRepository;
    private final AccountMonthStatisticRepository accountMonthStatisticRepository;
    private final AccountStatisticMapper accountStatisticMapper;
    private final CountPerAgeMapper countPerAgeMapper;
    private final AccountMonthStatisticMapper accountMonthStatisticMapper;
    private final AccountStatisticRepository accountStatisticRepository;
    private final AccountFeignClient accountFeignClient;
    private final DateTimeUtils dateTimeUtils;

    public AccountStatistic findAccountStatistic(AccountStatisticRequestDto requestDto) {
        ZonedDateTime startCurrentDay = dateTimeUtils.startDay(ZonedDateTime.now());
        Optional<AccountStatistic> foundAccountStatistic = loadAccountStatistic(requestDto.getDate());
        if (foundAccountStatistic.isPresent()) {
            return foundAccountStatistic.get();
        }
        AccountStatisticResponseDto oneMonthStatistic = getRemoteAccountStatistic(startCurrentDay,
                startCurrentDay, requestDto.getDate());
        AccountStatistic accountStatistic = accountStatisticMapper.map(oneMonthStatistic, false);
        if (requestDto.getDate().isBefore(startCurrentDay)) {
            accountStatistic = accountStatisticRepository.save(accountStatistic);
            saveCountPerAge(oneMonthStatistic.getCountPerAge(), accountStatistic);
        }
        return accountStatistic;
    }

    public List<AccountMonthStatistic> findAccountMonthStatistics(AccountStatisticRequestDto requestDto) {
        List<AccountMonthStatistic> accountMonthStatistics = new ArrayList<>();
        ZonedDateTime startMonth = dateTimeUtils.startMonth(requestDto.getFirstMonth());
        ZonedDateTime endMonth = dateTimeUtils.endMonth(requestDto.getLastMonth());

        while (!startMonth.isAfter(endMonth)) {
            List<AccountMonthStatistic> monthStatistic = loadAccountMonthStatistic(startMonth,
                    dateTimeUtils.endMonth(startMonth));
            if (monthStatistic.isEmpty()) {
                AccountStatisticResponseDto oneMonthStatistic = getRemoteAccountStatistic(startMonth,
                        dateTimeUtils.endMonth(startMonth), requestDto.getDate());
                monthStatistic = accountMonthStatisticMapper
                        .map(oneMonthStatistic.getCountPerMonth(), false);
                if (startMonth.isBefore(dateTimeUtils.startMonth(ZonedDateTime.now()))) {
                    saveAccountMonthStatistic(oneMonthStatistic.getCountPerMonth());
                }
            }
            accountMonthStatistics.addAll(monthStatistic);
            startMonth = startMonth.plusMonths(1);
        }
        return accountMonthStatistics;
    }

    private AccountStatisticResponseDto getRemoteAccountStatistic(ZonedDateTime firstMonth,
                                                                  ZonedDateTime lastMonth,
                                                                  ZonedDateTime dateTime) {
        AccountStatisticRequestDto requestDto = new AccountStatisticRequestDto();
        requestDto.setDate(dateTime);
        requestDto.setFirstMonth(firstMonth);
        requestDto.setLastMonth(lastMonth);
        return accountFeignClient.getAccountStatistic(requestDto).getBody();
    }

    public List<AccountMonthStatistic> loadAccountMonthStatistic(ZonedDateTime firstMonth,
                                                                 ZonedDateTime lastMonth) {
        MonthStatisticSearchDto monthStatisticSearchDto = new MonthStatisticSearchDto()
                .setFirstMonth(firstMonth).setLastMonth(lastMonth);
        List<AccountMonthStatistic> accountMonthStatistics = accountMonthStatisticRepository
                .findAll(getMonthStatisticSpec(monthStatisticSearchDto));
        return accountMonthStatistics;
    }

    public Optional<AccountStatistic> loadAccountStatistic(ZonedDateTime date) {
        return accountStatisticRepository
                .findByDateBetween(dateTimeUtils.startDay(date), dateTimeUtils.endDay(date))
                .stream()
                .findFirst();
    }

    public List<CountPerAge> saveCountPerAge(List<AccountCountPerAge> accountCountPerAges,
                                             AccountStatistic accountStatistic) {
        List<CountPerAge> countPerAges = countPerAgeMapper
                .map(accountCountPerAges, accountStatistic, false);
        return countPerAgeRepository.saveAll(countPerAges);
    }

    public List<AccountMonthStatistic> saveAccountMonthStatistic(List<StatisticPerDateDto>
                                                                         statisticPerDateDto) {
        List<AccountMonthStatistic> accountMonthStatistic = accountMonthStatisticMapper
                .map(statisticPerDateDto, false);
        return accountMonthStatisticRepository.saveAll(accountMonthStatistic);
    }

    public static Specification<AccountMonthStatistic> getMonthStatisticSpec(MonthStatisticSearchDto monthStatisticSearchDto) {
        return getBaseSpecification(monthStatisticSearchDto)
                .and(between(AccountMonthStatistic_.date, monthStatisticSearchDto.getFirstMonth(),
                        monthStatisticSearchDto.getLastMonth(), true));
    }
}
