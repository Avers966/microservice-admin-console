package ru.skillbox.diplom.group35.microservice.admin_console.impl.repository.like;

import org.springframework.stereotype.Repository;
import ru.skillbox.diplom.group35.library.core.repository.BaseRepository;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.like.CountLikePerHour;

@Repository
public interface CountLikePerHourRepository extends BaseRepository<CountLikePerHour> {

}
