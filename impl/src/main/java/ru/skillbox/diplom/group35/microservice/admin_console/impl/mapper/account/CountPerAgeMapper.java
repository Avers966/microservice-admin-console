package ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.account;

import org.mapstruct.*;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountCountPerAge;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account.AccountStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account.CountPerAge;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountPerAgeMapper {
    @Mapping(target = "accountStatistic", ignore = true)
    CountPerAge map(AccountCountPerAge accountCountPerAge);

    List<AccountCountPerAge> map(List<CountPerAge> countPerAges);

    @Mapping(target = "accountStatistic", ignore = true)
    default List<CountPerAge> map(List<AccountCountPerAge> accountCountPerAge,
                                  AccountStatistic accountStatistic, Boolean isDeleted) {
        return accountCountPerAge.stream().map(m ->  {
            CountPerAge countPerAge = this.map(m);
            countPerAge.setAccountStatistic(accountStatistic);
            countPerAge.setIsDeleted(isDeleted);
            return countPerAge;
        }).collect(Collectors.toList());
    }

}
