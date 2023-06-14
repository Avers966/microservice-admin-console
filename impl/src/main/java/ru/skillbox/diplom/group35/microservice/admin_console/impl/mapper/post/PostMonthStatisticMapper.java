package ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.post;

import org.mapstruct.Mapper;
import ru.skillbox.diplom.group35.library.core.dto.statistic.StatisticPerDateDto;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.post.PostMonthStatistic;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PostMonthStatisticMapper {

    PostMonthStatistic map(StatisticPerDateDto countPerMonth);
    List<StatisticPerDateDto> map(List<PostMonthStatistic> postMonthStatistics);

    default List<PostMonthStatistic> map(List<StatisticPerDateDto> countPerMonth, Boolean isDeleted) {
        return countPerMonth.stream().map(m ->  {
            PostMonthStatistic postMonthStatistic = this.map(m);
            postMonthStatistic.setIsDeleted(isDeleted);
            return postMonthStatistic;
        }).collect(Collectors.toList());
    }
}
