package ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.like;

import org.mapstruct.Mapper;
import ru.skillbox.diplom.group35.library.core.dto.statistic.StatisticPerDateDto;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.like.LikeMonthStatistic;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface LikeMonthStatisticMapper {

    LikeMonthStatistic map(StatisticPerDateDto countPerMonth);
    List<StatisticPerDateDto> map(List<LikeMonthStatistic> likeMonthStatistics);

    default List<LikeMonthStatistic> map(List<StatisticPerDateDto> countPerMonth, Boolean isDeleted) {
        return countPerMonth.stream().map(m ->  {
            LikeMonthStatistic likeMonthStatistic = this.map(m);
            likeMonthStatistic.setIsDeleted(isDeleted);
            return likeMonthStatistic;
        }).collect(Collectors.toList());
    }
}
