package ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skillbox.diplom.group35.library.core.dto.statistic.StatisticPerDateDto;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.comment.CommentStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.comment.CountCommentPerHour;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountCommentPerHourMapper {
    @Mapping(target = "commentStatistic", ignore = true)
    CountCommentPerHour map(StatisticPerDateDto statisticPerDateDto);

    List<StatisticPerDateDto> map(List<CountCommentPerHour> countCommentPerHours);

    @Mapping(target = "commentStatistic", ignore = true)
    default List<CountCommentPerHour> map(List<StatisticPerDateDto> statisticPerDateDtos,
                                          CommentStatistic commentStatistic, Boolean isDeleted) {
        return statisticPerDateDtos.stream().map(m ->  {
            CountCommentPerHour countCommentPerHour = this.map(m);
            countCommentPerHour.setCommentStatistic(commentStatistic);
            countCommentPerHour.setIsDeleted(isDeleted);
            return countCommentPerHour;
        }).collect(Collectors.toList());
    }

}
