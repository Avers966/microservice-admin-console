package ru.skillbox.diplom.group35.microservice.admin_console.impl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.microservice.account.api.client.AccountFeignClient;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account.AccountCountPerAge;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.StatisticPerDateDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account.AccountStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account.AccountStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.comment.CommentStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.comment.CommentStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.like.LikeStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.like.LikeStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.post.PostStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.post.PostStatisticResponseDto;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StatisticService {

    private final AccountFeignClient accountFeignClient;
    private final MockAccountStatistic mockAccountStatistic;
    private final MockCommentStatistic mockCommentStatistic;
    private final MockLikeStatistic mockLikeStatistic;
    private final MockPostStatistic mockPostStatistic;

    public AccountStatisticResponseDto getAccountStatistic(AccountStatisticRequestDto accountStatisticRequestDto) {
        return mockAccountStatistic.getStatistic(accountStatisticRequestDto);
    }

    public CommentStatisticResponseDto getCommentStatistic(CommentStatisticRequestDto commentStatisticRequestDto) {
        return mockCommentStatistic.getStatistic(commentStatisticRequestDto);
    }

    public LikeStatisticResponseDto getLikeStatistic(LikeStatisticRequestDto likeStatisticRequestDto) {
        return mockLikeStatistic.getStatistic(likeStatisticRequestDto);
    }

    public PostStatisticResponseDto getPostStatistic(PostStatisticRequestDto postStatisticRequestDto) {
        return mockPostStatistic.getStatistic(postStatisticRequestDto);
    }


}
