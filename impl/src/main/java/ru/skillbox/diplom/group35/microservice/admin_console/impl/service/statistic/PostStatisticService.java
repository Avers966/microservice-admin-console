package ru.skillbox.diplom.group35.microservice.admin_console.impl.service.statistic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.library.core.dto.statistic.StatisticPerDateDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.MonthStatisticSearchDto;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.post.CountPostPerHour;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.post.PostMonthStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.post.PostMonthStatistic_;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.post.PostStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.post.CountPostPerHourMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.post.PostMonthStatisticMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.post.PostStatisticMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.repository.post.CountPostPerHourRepository;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.repository.post.PostMonthStatisticRepository;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.repository.post.PostStatisticRepository;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.utils.DateTimeUtils;
import ru.skillbox.diplom.group35.microservice.post.dto.statistic.PostStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.post.dto.statistic.StatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.post.resource.client.StatisticFeignClient;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.skillbox.diplom.group35.library.core.utils.SpecificationUtil.between;
import static ru.skillbox.diplom.group35.library.core.utils.SpecificationUtil.getBaseSpecification;

@Service
@RequiredArgsConstructor
public class PostStatisticService {

    private final PostMonthStatisticRepository postMonthStatisticRepository;
    private final PostStatisticMapper postStatisticMapper;
    private final CountPostPerHourMapper countPostPerHourMapper;
    private final PostMonthStatisticMapper postMonthStatisticMapper;
    private final PostStatisticRepository postStatisticRepository;
    private final CountPostPerHourRepository countPostPerHourRepository;
    private final StatisticFeignClient statisticFeignClient;
    private final DateTimeUtils dateTimeUtils;

    public PostStatistic findPostStatistic(PostStatisticRequestDto requestDto) {
        ZonedDateTime startCurrentDay = dateTimeUtils.startDay(ZonedDateTime.now());
        ZonedDateTime startMonth = dateTimeUtils.startMonth(requestDto.getFirstMonth());
        ZonedDateTime endMonth = dateTimeUtils.endMonth(requestDto.getLastMonth());
        Optional<PostStatistic> foundPostStatistic = loadPostStatistic(requestDto.getDate());
        if (foundPostStatistic.isPresent()) {
            return foundPostStatistic.get();
        }
        StatisticResponseDto oneMonthStatistic = getRemotePostStatistic(startMonth,
                endMonth, requestDto.getDate());
        PostStatistic postStatistic = postStatisticMapper.map(oneMonthStatistic, false);
        if (requestDto.getDate().isBefore(startCurrentDay)) {
            postStatistic = postStatisticRepository.save(postStatistic);
            saveCountPerHour(oneMonthStatistic.getCountPerHours(), postStatistic);
        }
        return postStatistic;
    }

    public List<PostMonthStatistic> findPostMonthStatistics(PostStatisticRequestDto requestDto) {
        List<PostMonthStatistic> postMonthStatistics = new ArrayList<>();
        ZonedDateTime startMonth = dateTimeUtils.startMonth(requestDto.getFirstMonth());
        ZonedDateTime endMonth = dateTimeUtils.endMonth(requestDto.getLastMonth());

        while (!startMonth.isAfter(endMonth)) {
            List<PostMonthStatistic> monthStatistic = loadPostMonthStatistic(startMonth,
                    dateTimeUtils.endMonth(startMonth));
            if (monthStatistic.isEmpty()) {
                StatisticResponseDto oneMonthStatistic = getRemotePostStatistic(startMonth,
                        dateTimeUtils.endMonth(startMonth), requestDto.getDate());
                monthStatistic = postMonthStatisticMapper
                        .map(oneMonthStatistic.getCountPerMonth(), false);
                if (startMonth.isBefore(dateTimeUtils.startMonth(ZonedDateTime.now()))) {
                    savePostMonthStatistic(oneMonthStatistic.getCountPerMonth());
                }
            }
            postMonthStatistics.addAll(monthStatistic);
            startMonth = startMonth.plusMonths(1);
        }
        return postMonthStatistics;
    }

    private StatisticResponseDto getRemotePostStatistic(ZonedDateTime firstMonth,
                                                               ZonedDateTime lastMonth,
                                                               ZonedDateTime dateTime) {
        PostStatisticRequestDto requestDto = new PostStatisticRequestDto();
        requestDto.setDate(dateTime);
        requestDto.setFirstMonth(firstMonth);
        requestDto.setLastMonth(lastMonth);
        return statisticFeignClient.getPostStatistic(requestDto).getBody();
    }

    public List<PostMonthStatistic> loadPostMonthStatistic(ZonedDateTime firstMonth,
                                                              ZonedDateTime lastMonth) {
        MonthStatisticSearchDto monthStatisticSearchDto = new MonthStatisticSearchDto()
                .setFirstMonth(firstMonth).setLastMonth(lastMonth);
        List<PostMonthStatistic> postMonthStatistics = postMonthStatisticRepository
                .findAll(getMonthStatisticSpec(monthStatisticSearchDto));
        return postMonthStatistics;
    }

    public Optional<PostStatistic> loadPostStatistic(ZonedDateTime date) {
        return postStatisticRepository
                .findByDateBetween(dateTimeUtils.startDay(date), dateTimeUtils.endDay(date))
                .stream()
                .findFirst();
    }

    public List<PostMonthStatistic> savePostMonthStatistic(List<StatisticPerDateDto>
                                                                         statisticPerDateDto) {
        List<PostMonthStatistic> postMonthStatistic = postMonthStatisticMapper
                .map(statisticPerDateDto, false);
        return postMonthStatisticRepository.saveAll(postMonthStatistic);
    }

    public List<CountPostPerHour> saveCountPerHour(List<StatisticPerDateDto> postCountPerHours,
                                                   PostStatistic postStatistic) {
        List<CountPostPerHour> countPostPerHours = countPostPerHourMapper
                .map(postCountPerHours, postStatistic, false);
        return countPostPerHourRepository.saveAll(countPostPerHours);
    }

    public static Specification<PostMonthStatistic> getMonthStatisticSpec(MonthStatisticSearchDto monthStatisticSearchDto) {
        return getBaseSpecification(monthStatisticSearchDto)
                .and(between(PostMonthStatistic_.date, monthStatisticSearchDto.getFirstMonth(),
                        monthStatisticSearchDto.getLastMonth(), true));
    }
}
