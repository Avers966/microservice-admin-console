package ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.like;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.like.LikeMonthStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.like.LikeStatistic;
import ru.skillbox.diplom.group35.microservice.post.dto.statistic.PostStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.post.dto.statistic.StatisticResponseDto;

import java.util.List;

/**
 * LikeResponseMapper
 *
 * @author Georgii Vinogradov
 */

@Mapper(componentModel = "spring")
public abstract class LikeResponseMapper {
    @Autowired
    private CountLikePerHourMapper countLikePerHourMapper;
    @Autowired
    private LikeMonthStatisticMapper likeMonthStatisticMapper;

    public StatisticResponseDto map(PostStatisticRequestDto requestDto,
                                    List<LikeMonthStatistic> likeMonthStatistics,
                                    LikeStatistic likeStatistic) {
        StatisticResponseDto responseDto = new StatisticResponseDto();
        responseDto.setDate(requestDto.getDate());
        responseDto.setCount(likeStatistic.getCount());
        responseDto.setCountPerHours(countLikePerHourMapper.map(likeStatistic.getCountLikePerHour()));
        responseDto.setCountPerMonth(likeMonthStatisticMapper.map(likeMonthStatistics));
        return responseDto;
    }

}
