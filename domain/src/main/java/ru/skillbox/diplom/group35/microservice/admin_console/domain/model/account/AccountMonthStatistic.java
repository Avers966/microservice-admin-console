package ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;

/**
 * AccountMonthStatistic
 *
 * @author Georgii Vinogradov
 */


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "account_month_statistic")
public class AccountMonthStatistic extends BaseEntity {
    private ZonedDateTime date;
    private Integer count;
}
