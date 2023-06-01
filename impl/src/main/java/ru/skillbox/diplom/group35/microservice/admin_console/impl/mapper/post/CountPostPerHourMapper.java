package ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skillbox.diplom.group35.library.core.dto.statistic.StatisticPerDateDto;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.post.CountPostPerHour;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.post.PostStatistic;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountPostPerHourMapper {
    @Mapping(target = "postStatistic", ignore = true)
    CountPostPerHour map(StatisticPerDateDto statisticPerDateDto);

    List<StatisticPerDateDto> map(List<CountPostPerHour> countPostPerHours);

    @Mapping(target = "postStatistic", ignore = true)
    default List<CountPostPerHour> map(List<StatisticPerDateDto> statisticPerDateDtos,
                                       PostStatistic postStatistic, Boolean isDeleted) {
        return statisticPerDateDtos.stream().map(m ->  {
            CountPostPerHour countPostPerHour = this.map(m);
            countPostPerHour.setPostStatistic(postStatistic);
            countPostPerHour.setIsDeleted(isDeleted);
            return countPostPerHour;
        }).collect(Collectors.toList());
    }

}
