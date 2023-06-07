package ru.skillbox.diplom.group35.microservice.admin_console.impl.repository.post;

import org.springframework.stereotype.Repository;
import ru.skillbox.diplom.group35.library.core.repository.BaseRepository;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.post.CountPostPerHour;

@Repository
public interface CountPostPerHourRepository extends BaseRepository<CountPostPerHour> {

}
