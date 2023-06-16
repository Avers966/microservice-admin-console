package ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.comment.CommentStatistic;
import ru.skillbox.diplom.group35.microservice.post.dto.statistic.StatisticResponseDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentStatisticMapper {
    @Mapping(target = "countCommentPerHour", source = "responseDto.countPerHours")
    CommentStatistic map(StatisticResponseDto responseDto, Boolean isDeleted);
}
