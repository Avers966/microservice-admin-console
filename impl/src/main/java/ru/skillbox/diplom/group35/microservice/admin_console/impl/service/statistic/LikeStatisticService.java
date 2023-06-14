package ru.skillbox.diplom.group35.microservice.admin_console.impl.service.statistic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.library.core.dto.statistic.StatisticPerDateDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.MonthStatisticSearchDto;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.like.CountLikePerHour;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.like.LikeMonthStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.like.LikeMonthStatistic_;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.like.LikeStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.like.CountLikePerHourMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.like.LikeMonthStatisticMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.like.LikeStatisticMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.repository.like.CountLikePerHourRepository;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.repository.like.LikeMonthStatisticRepository;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.repository.like.LikeStatisticRepository;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.utils.DateTimeUtils;
import ru.skillbox.diplom.group35.microservice.post.dto.StatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.post.dto.like.LikeStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.post.resource.client.PostFeignClient;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.skillbox.diplom.group35.library.core.utils.SpecificationUtil.between;
import static ru.skillbox.diplom.group35.library.core.utils.SpecificationUtil.getBaseSpecification;

@Service
@RequiredArgsConstructor
public class LikeStatisticService {

    private final LikeMonthStatisticRepository likeMonthStatisticRepository;
    private final LikeStatisticMapper likeStatisticMapper;
    private final CountLikePerHourMapper countLikePerHourMapper;
    private final LikeMonthStatisticMapper likeMonthStatisticMapper;
    private final LikeStatisticRepository likeStatisticRepository;
    private final CountLikePerHourRepository countLikePerHourRepository;
    private final PostFeignClient postFeignClient;
    private final DateTimeUtils dateTimeUtils;

    public LikeStatistic findLikeStatistic(LikeStatisticRequestDto requestDto) {
        ZonedDateTime startCurrentDay = dateTimeUtils.startDay(ZonedDateTime.now());
        Optional<LikeStatistic> foundLikeStatistic = loadLikeStatistic(requestDto.getDate());
        if (foundLikeStatistic.isPresent()) {
            return foundLikeStatistic.get();
        }
        StatisticResponseDto oneMonthStatistic = getRemoteLikeStatistic(startCurrentDay,
                startCurrentDay, requestDto.getDate());
        LikeStatistic liketStatistic = likeStatisticMapper.map(oneMonthStatistic, false);
        if (requestDto.getDate().isBefore(startCurrentDay)) {
            liketStatistic = likeStatisticRepository.save(liketStatistic);
            saveCountPerHour(oneMonthStatistic.getCountPerHours(), liketStatistic);
        }
        return liketStatistic;
    }

    public List<LikeMonthStatistic> findLikeMonthStatistics(LikeStatisticRequestDto requestDto) {
        List<LikeMonthStatistic> likeMonthStatistics = new ArrayList<>();
        ZonedDateTime startMonth = dateTimeUtils.startMonth(requestDto.getFirstMonth());
        ZonedDateTime endMonth = dateTimeUtils.endMonth(requestDto.getLastMonth());

        while (!startMonth.isAfter(endMonth)) {
            List<LikeMonthStatistic> monthStatistic = loadLikeMonthStatistic(startMonth,
                    dateTimeUtils.endMonth(startMonth));
            if (monthStatistic.isEmpty()) {
                StatisticResponseDto oneMonthStatistic = getRemoteLikeStatistic(startMonth,
                        dateTimeUtils.endMonth(startMonth), requestDto.getDate());
                monthStatistic = likeMonthStatisticMapper
                        .map(oneMonthStatistic.getCountPerMonth(), false);
                if (!monthStatistic.isEmpty() && monthStatistic.get(0).getDate().isBefore(dateTimeUtils.startMonth(ZonedDateTime.now()))) {
                    saveLikeMonthStatistic(oneMonthStatistic.getCountPerMonth());
                }
            }
            likeMonthStatistics.addAll(monthStatistic);
            startMonth = startMonth.plusMonths(1);
        }
        return likeMonthStatistics;
    }

    private StatisticResponseDto getRemoteLikeStatistic(ZonedDateTime firstMonth,
                                                        ZonedDateTime lastMonth,
                                                        ZonedDateTime dateTime) {
        LikeStatisticRequestDto requestDto = new LikeStatisticRequestDto();
        requestDto.setDate(dateTime);
        requestDto.setFirstMonth(firstMonth);
        requestDto.setLastMonth(lastMonth);
        return postFeignClient.getLikeStatistic(requestDto).getBody();
    }

    public List<LikeMonthStatistic> loadLikeMonthStatistic(ZonedDateTime firstMonth,
                                                              ZonedDateTime lastMonth) {
        MonthStatisticSearchDto monthStatisticSearchDto = new MonthStatisticSearchDto()
                .setFirstMonth(firstMonth).setLastMonth(lastMonth);
        List<LikeMonthStatistic> likeMonthStatistics = likeMonthStatisticRepository
                .findAll(getMonthStatisticSpec(monthStatisticSearchDto));
        return likeMonthStatistics;
    }

    public Optional<LikeStatistic> loadLikeStatistic(ZonedDateTime date) {
        return likeStatisticRepository
                .findByDateBetween(dateTimeUtils.startDay(date), dateTimeUtils.endDay(date))
                .stream()
                .findFirst();
    }

    public List<LikeMonthStatistic> saveLikeMonthStatistic(List<StatisticPerDateDto>
                                                                         statisticPerDateDto) {
        List<LikeMonthStatistic> likeMonthStatistic = likeMonthStatisticMapper
                .map(statisticPerDateDto, false);
        return likeMonthStatisticRepository.saveAll(likeMonthStatistic);
    }

    public List<CountLikePerHour> saveCountPerHour(List<StatisticPerDateDto> likeCountPerHours,
                                                   LikeStatistic likeStatistic) {
        List<CountLikePerHour> countLikePerHours = countLikePerHourMapper
                .map(likeCountPerHours, likeStatistic, false);
        return countLikePerHourRepository.saveAll(countLikePerHours);
    }

    public static Specification<LikeMonthStatistic> getMonthStatisticSpec(MonthStatisticSearchDto monthStatisticSearchDto) {
        return getBaseSpecification(monthStatisticSearchDto)
                .and(between(LikeMonthStatistic_.date, monthStatisticSearchDto.getFirstMonth(),
                        monthStatisticSearchDto.getLastMonth(), true));
    }
}
