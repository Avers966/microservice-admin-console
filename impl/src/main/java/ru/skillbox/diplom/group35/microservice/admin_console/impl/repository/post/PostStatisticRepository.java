package ru.skillbox.diplom.group35.microservice.admin_console.impl.repository.post;

import org.springframework.stereotype.Repository;
import ru.skillbox.diplom.group35.library.core.repository.BaseRepository;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.post.PostStatistic;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * PostStatisticRepository
 *
 * @author Georgii Vinogradov
 */

@Repository
public interface PostStatisticRepository extends BaseRepository<PostStatistic> {
    List<PostStatistic> findByDateBetween(ZonedDateTime startOfDay, ZonedDateTime endOfDay);
}
