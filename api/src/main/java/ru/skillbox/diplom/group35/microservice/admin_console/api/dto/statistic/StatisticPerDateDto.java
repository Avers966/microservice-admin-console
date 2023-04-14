package ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * StatisticPerDateDto
 *
 * @author Georgii Vinogradov
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticPerDateDto {
    private ZonedDateTime date;
    private Integer count;
}
