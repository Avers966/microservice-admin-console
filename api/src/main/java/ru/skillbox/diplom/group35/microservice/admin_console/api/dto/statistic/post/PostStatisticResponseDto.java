package ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.StatisticPerDateDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account.AccountCountPerAge;

import java.util.List;

/**
 * PostStatisticResponseDto
 *
 * @author Georgii Vinogradov
 */

@Getter
@Setter
@NoArgsConstructor
public class PostStatisticResponseDto extends StatisticPerDateDto {
    private List<StatisticPerDateDto> countPerHours;
    private List<StatisticPerDateDto> countPerMonth;
}
