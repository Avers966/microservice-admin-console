package ru.skillbox.diplom.group35.microservice.admin_console.domain.model.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * CountPerHour
 *
 * @author Georgii Vinogradov
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "count_post_per_hour")
public class CountPostPerHour extends BaseEntity {

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "count")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "post_statistic_id", nullable = false)
    private PostStatistic postStatistic;
}