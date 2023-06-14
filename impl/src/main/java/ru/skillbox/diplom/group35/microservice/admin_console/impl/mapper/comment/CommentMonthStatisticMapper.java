package ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.comment;

import org.mapstruct.Mapper;
import ru.skillbox.diplom.group35.library.core.dto.statistic.StatisticPerDateDto;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.comment.CommentMonthStatistic;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CommentMonthStatisticMapper {

    CommentMonthStatistic map(StatisticPerDateDto countPerMonth);
    List<StatisticPerDateDto> map(List<CommentMonthStatistic> commentMonthStatistics);

    default List<CommentMonthStatistic> map(List<StatisticPerDateDto> countPerMonth, Boolean isDeleted) {
        return countPerMonth.stream().map(m ->  {
            CommentMonthStatistic commentMonthStatistic = this.map(m);
            commentMonthStatistic.setIsDeleted(isDeleted);
            return commentMonthStatistic;
        }).collect(Collectors.toList());
    }
}
