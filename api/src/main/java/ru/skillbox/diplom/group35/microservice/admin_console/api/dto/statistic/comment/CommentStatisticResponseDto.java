package ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.comment;

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
public class CommentStatisticResponseDto extends StatisticPerDateDto {
    private List<StatisticPerDateDto> countPerHours;
    private List<StatisticPerDateDto> countPerMonth;
}
