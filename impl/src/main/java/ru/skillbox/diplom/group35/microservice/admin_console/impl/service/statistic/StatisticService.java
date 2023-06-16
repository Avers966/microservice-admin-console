package ru.skillbox.diplom.group35.microservice.admin_console.impl.service.statistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account.AccountMonthStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.account.AccountStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.comment.CommentMonthStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.comment.CommentStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.like.LikeMonthStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.like.LikeStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.post.PostMonthStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.domain.model.post.PostStatistic;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.account.AccountResponseMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.comment.CommentResponseMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.like.LikeResponseMapper;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.mapper.post.PostResponseMapper;
import ru.skillbox.diplom.group35.microservice.post.dto.statistic.PostStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.post.dto.statistic.StatisticResponseDto;

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
    private final CommentStatisticService commentStatisticService;
    private final LikeStatisticService likeStatisticService;

    private final AccountResponseMapper accountResponseMapper;
    private final PostResponseMapper postResponseMapper;
    private final CommentResponseMapper commentResponseMapper;
    private final LikeResponseMapper likeResponseMapper;

    public AccountStatisticResponseDto getAccountStatistic(AccountStatisticRequestDto requestDto) {
        List<AccountMonthStatistic> accountMonthStatistics = accountStatisticService
                .findAccountMonthStatistics(requestDto);
        AccountStatistic accountStatistic = accountStatisticService.findAccountStatistic(requestDto);
        return accountResponseMapper.map(requestDto, accountMonthStatistics, accountStatistic);
    }

    public StatisticResponseDto getCommentStatistic(PostStatisticRequestDto requestDto) {
        List<CommentMonthStatistic> commentMonthStatistics = commentStatisticService
                .findCommentMonthStatistics(requestDto);
        CommentStatistic commentStatistic = commentStatisticService.findCommentStatistic(requestDto);
        return commentResponseMapper.map(requestDto, commentMonthStatistics, commentStatistic);
    }

    public StatisticResponseDto getLikeStatistic(PostStatisticRequestDto requestDto) {
        List<LikeMonthStatistic> likeMonthStatistics = likeStatisticService
                .findLikeMonthStatistics(requestDto);
        LikeStatistic likeStatistic = likeStatisticService
                .findLikeStatistic(requestDto);
        return likeResponseMapper.map(requestDto, likeMonthStatistics, likeStatistic);
    }

    public StatisticResponseDto getPostStatistic(PostStatisticRequestDto requestDto) {
        List<PostMonthStatistic> postMonthStatistics = postStatisticService
                .findPostMonthStatistics(requestDto);
        PostStatistic postStatistic = postStatisticService.findPostStatistic(requestDto);
        return postResponseMapper.map(requestDto, postMonthStatistics, postStatistic);
    }
}
