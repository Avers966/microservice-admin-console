package ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.skillbox.diplom.group35.library.core.dto.base.BaseSearchDto;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MonthStatisticSearchDto extends BaseSearchDto {
    ZonedDateTime firstMonth;
    ZonedDateTime lastMonth;
}
