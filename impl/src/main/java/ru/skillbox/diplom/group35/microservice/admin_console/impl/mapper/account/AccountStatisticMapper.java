package ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.account;

import org.mapstruct.*;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account.AccountStatistic;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountStatisticMapper {
    AccountStatistic map(AccountStatisticResponseDto responseDto, Boolean isDeleted);
}
