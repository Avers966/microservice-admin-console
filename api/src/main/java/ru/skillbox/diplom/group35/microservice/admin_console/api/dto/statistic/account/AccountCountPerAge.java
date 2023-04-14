package ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AgeCount
 *
 * @author Georgii Vinogradov
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCountPerAge {
    private Integer age;
    private Integer count;
}
