package ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.account;

import org.mapstruct.Mapper;
import ru.skillbox.diplom.group35.library.core.dto.statistic.StatisticPerDateDto;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account.AccountMonthStatistic;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface AccountMonthStatisticMapper {

    AccountMonthStatistic map(StatisticPerDateDto countPerMonth);
    List<StatisticPerDateDto> map(List<AccountMonthStatistic> accountMonthStatistics);

    default List<AccountMonthStatistic> map(List<StatisticPerDateDto> countPerMonth, Boolean isDeleted) {
        return countPerMonth.stream().map(m ->  {
            AccountMonthStatistic accountMonthStatistic = this.map(m);
            accountMonthStatistic.setIsDeleted(isDeleted);
            return accountMonthStatistic;
        }).collect(Collectors.toList());
    }
}
