package ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity;

import javax.persistence.*;

/**
 * CountPerAge
 *
 * @author Georgii Vinogradov
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "count_per_age")
public class CountPerAge extends BaseEntity {

    @Column(name = "age")
    private Integer age;

    @Column(name = "count")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "account_statistic_id", nullable = false)
    private AccountStatistic accountStatistic;
}
