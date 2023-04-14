package ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.like;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.StatisticPerDateDto;

import java.util.List;

/**
 * LikeStatisticResponseDto
 *
 * @author Georgii Vinogradov
 */

@Getter
@Setter
@NoArgsConstructor
public class LikeStatisticResponseDto extends StatisticPerDateDto {
    private List<StatisticPerDateDto> countPerHours;
    private List<StatisticPerDateDto> countPerMonth;
}
