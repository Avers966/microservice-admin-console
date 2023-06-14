package ru.skillbox.diplom.group35.microservice.admin_console.impl.repository.comment;

import org.springframework.stereotype.Repository;
import ru.skillbox.diplom.group35.library.core.repository.BaseRepository;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.comment.CommentMonthStatistic;

/**
 * CommentMonthStatisticRepository
 *
 * @author Georgii Vinogradov
 */

@Repository
public interface CommentMonthStatisticRepository extends BaseRepository<CommentMonthStatistic> {

}
