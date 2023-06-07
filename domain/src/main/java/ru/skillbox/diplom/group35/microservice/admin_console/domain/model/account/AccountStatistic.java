package ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

/**
 * AccountStatistic
 *
 * @author Georgii Vinogradov
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "account_statistic")
public class AccountStatistic extends BaseEntity {

    @Column(name = "date", nullable = false)
    private ZonedDateTime date;

    @Column(name = "count")
    private Integer count;

    @Column(name = "count_per_age")
    @OneToMany(mappedBy = "accountStatistic")
    private List<CountPerAge> countPerAge;
}
