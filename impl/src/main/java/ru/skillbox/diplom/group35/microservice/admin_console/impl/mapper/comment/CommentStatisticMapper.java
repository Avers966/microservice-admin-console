package ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.comment;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.comment.CommentStatistic;
import ru.skillbox.diplom.group35.microservice.post.dto.StatisticResponseDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentStatisticMapper {
    CommentStatistic map(StatisticResponseDto responseDto, Boolean isDeleted);
}
