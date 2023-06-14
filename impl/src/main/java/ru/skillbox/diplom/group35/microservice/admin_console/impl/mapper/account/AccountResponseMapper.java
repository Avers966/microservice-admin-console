package ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.account;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account.AccountMonthStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account.AccountStatistic;

import java.util.List;

/**
 * AccountResponseMapper
 *
 * @author Georgii Vinogradov
 */

@Mapper(componentModel = "spring")
public abstract class AccountResponseMapper {
    @Autowired
    private CountPerAgeMapper countPerAgeMapper;
    @Autowired
    private AccountMonthStatisticMapper accountMonthStatisticMapper;

    public AccountStatisticResponseDto map(AccountStatisticRequestDto requestDto,
                                           List<AccountMonthStatistic> accountMonthStatistics,
                                           AccountStatistic accountStatistic) {
        AccountStatisticResponseDto responseDto = new AccountStatisticResponseDto();
        responseDto.setDate(requestDto.getDate());
        responseDto.setCount(accountStatistic.getCount());
        responseDto.setCountPerAge(countPerAgeMapper.map(accountStatistic.getCountPerAge()));
        responseDto.setCountPerMonth(accountMonthStatisticMapper.map(accountMonthStatistics));
        return responseDto;
    }

}
