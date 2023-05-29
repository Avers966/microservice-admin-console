package ru.skillbox.diplom.group35.microservice.admin_console.impl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.microservice.account.api.client.AccountFeignClient;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.post.dto.StatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.post.dto.comment.CommentStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.post.dto.like.LikeStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.post.dto.post.PostStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.post.resource.client.PostFeignClient;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
public class StatisticService {

    private final AccountFeignClient accountFeignClient;
    private final PostFeignClient postFeignClient;

    public AccountStatisticResponseDto getAccountStatistic(AccountStatisticRequestDto accountStatisticRequestDto) {
        ResponseEntity<AccountStatisticResponseDto> statisticFound =
                accountFeignClient.getAccountStatistic(accountStatisticRequestDto);
        return statisticFound.getBody();
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

    public StatisticResponseDto getPostStatistic(PostStatisticRequestDto postStatisticRequestDto) {
        ResponseEntity<StatisticResponseDto> statisticFound =
                postFeignClient.getPostStatistic(postStatisticRequestDto);
        return statisticFound.getBody();
    }
}
