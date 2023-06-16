package ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.like;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.like.LikeStatistic;
import ru.skillbox.diplom.group35.microservice.post.dto.statistic.StatisticResponseDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LikeStatisticMapper {
    @Mapping(target = "countLikePerHour", source = "responseDto.countPerMonth")
    LikeStatistic map(StatisticResponseDto responseDto, Boolean isDeleted);
}
