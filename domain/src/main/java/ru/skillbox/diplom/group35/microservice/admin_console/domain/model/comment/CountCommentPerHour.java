package ru.skillbox.diplom.group35.microservice.admin_console.domain.model.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * CountCommentPerHour
 *
 * @author Georgii Vinogradov
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "count_comment_per_hour")
public class CountCommentPerHour extends BaseEntity {

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "count")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "comment_statistic_id", nullable = false)
    private CommentStatistic commentStatistic;
}