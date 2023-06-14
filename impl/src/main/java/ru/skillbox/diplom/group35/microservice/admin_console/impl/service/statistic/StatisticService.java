package ru.skillbox.diplom.group35.microservice.admin_console.impl.service.statistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account.AccountMonthStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account.AccountStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.post.PostMonthStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.post.PostStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.account.AccountMonthStatisticMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.account.CountPerAgeMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.post.CountPostPerHourMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.post.PostMonthStatisticMapper;
import ru.skillbox.diplom.group35.microservice.post.dto.StatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.post.dto.comment.CommentStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.post.dto.like.LikeStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.post.dto.post.PostStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.post.resource.client.PostFeignClient;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
public class StatisticService {

    private final AccountStatisticService accountStatisticService;
    private final PostStatisticService postStatisticService;
    private final CountPerAgeMapper countPerAgeMapper;
    private final CountPostPerHourMapper countPostPerHourMapper;
    private final AccountMonthStatisticMapper accountMonthStatisticMapper;
    private final PostMonthStatisticMapper postMonthStatisticMapper;
    private final PostFeignClient postFeignClient;


    public AccountStatisticResponseDto getAccountStatistic(AccountStatisticRequestDto requestDto) {
        List<AccountMonthStatistic> accountMonthStatistics = accountStatisticService
                .findAccountMonthStatistics(requestDto);
        AccountStatistic accountStatistic = accountStatisticService
                .findAccountStatistic(requestDto);

        AccountStatisticResponseDto responseDto = new AccountStatisticResponseDto();
        responseDto.setDate(requestDto.getDate());
        responseDto.setCount(accountStatistic.getCount());
        responseDto.setCountPerAge(countPerAgeMapper.map(accountStatistic.getCountPerAge()));
        responseDto.setCountPerMonth(accountMonthStatisticMapper.map(accountMonthStatistics));
        return responseDto;
    }

    public StatisticResponseDto getCommentStatistic(CommentStatisticRequestDto commentStatisticRequestDto) {
        ResponseEntity<StatisticResponseDto> statisticFound =
                postFeignClient.getCommentStatistic(commentStatisticRequestDto);
        return statisticFound.getBody();
    }

    public StatisticResponseDto getLikeStatistic(LikeStatisticRequestDto likeStatisticRequestDto) {
        ResponseEntity<StatisticResponseDto> statisticFound =
                postFeignClient.getLikeStatistic(likeStatisticRequestDto);
        return statisticFound.getBody();
    }

    public StatisticResponseDto getPostStatistic(PostStatisticRequestDto requestDto) {
        List<PostMonthStatistic> postMonthStatistics = postStatisticService
                .findPostMonthStatistics(requestDto);
        PostStatistic postStatistic = postStatisticService
                .findPostStatistic(requestDto);

        StatisticResponseDto responseDto = new StatisticResponseDto();
        responseDto.setDate(requestDto.getDate());
        responseDto.setCount(postStatistic.getCount());
        responseDto.setCountPerHours(countPostPerHourMapper.map(postStatistic.getCountPostPerHour()));
        responseDto.setCountPerMonth(postMonthStatisticMapper.map(postMonthStatistics));
        return responseDto;
    }
}
