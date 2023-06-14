package ru.skillbox.diplom.group35.microservice.admin_console.impl.repository.like;

import org.springframework.stereotype.Repository;
import ru.skillbox.diplom.group35.library.core.repository.BaseRepository;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.like.LikeStatistic;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * LikeStatisticRepository
 *
 * @author Georgii Vinogradov
 */

@Repository
public interface LikeStatisticRepository extends BaseRepository<LikeStatistic> {
    List<LikeStatistic> findByDateBetween(ZonedDateTime startOfDay, ZonedDateTime endOfDay);
}
