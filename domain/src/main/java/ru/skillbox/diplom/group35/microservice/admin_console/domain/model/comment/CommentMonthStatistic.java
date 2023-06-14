package ru.skillbox.diplom.group35.microservice.admin_console.domain.model.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;

/**
 * CommentMonthStatistic
 *
 * @author Georgii Vinogradov
 */


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "comment_month_statistic")
public class CommentMonthStatistic extends BaseEntity {

    @Column(name = "date", nullable = false)
    private ZonedDateTime date;

    @Column(name = "count")
    private Integer count;

    @Column(name = "deleted_count")
    private Integer deletedCount;
}
