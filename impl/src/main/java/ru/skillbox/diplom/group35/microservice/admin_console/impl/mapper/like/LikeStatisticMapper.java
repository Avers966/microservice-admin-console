package ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.like;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.like.LikeStatistic;
import ru.skillbox.diplom.group35.microservice.post.dto.StatisticResponseDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LikeStatisticMapper {
    LikeStatistic map(StatisticResponseDto responseDto, Boolean isDeleted);
}
