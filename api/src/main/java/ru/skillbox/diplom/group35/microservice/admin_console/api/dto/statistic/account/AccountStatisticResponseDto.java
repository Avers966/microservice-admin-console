package ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.StatisticPerDateDto;

import java.util.List;

/**
 * AccountStatisticResponseDto
 *
 * @author Georgii Vinogradov
 */

@Getter
@Setter
@NoArgsConstructor
public class AccountStatisticResponseDto extends StatisticPerDateDto {
    private Integer maleCount;
    private Integer femaleCount;
    private Integer blankGenderCount;
    private List<AccountCountPerAge> countPerAge;
    private List<StatisticPerDateDto> countPerMonth;
}
