package ru.skillbox.diplom.group35.microservice.admin_console.impl.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.diplom.group35.library.core.annotation.EnableExceptionHandler;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account.AccountStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account.AccountStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.comment.CommentStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.comment.CommentStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.like.LikeStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.like.LikeStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.post.PostStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.post.PostStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.resource.StatisticController;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.service.StatisticService;


@Slf4j
@RestController
@EnableExceptionHandler
@RequiredArgsConstructor
public class StatisticControllerImpl implements StatisticController {

    private final StatisticService statisticService;

    @Override
    public ResponseEntity<AccountStatisticResponseDto> getAccountStatistic(AccountStatisticRequestDto accountStatisticRequestDto) {
        log.info("account get statistic");
        return ResponseEntity.ok(statisticService.getAccountStatistic(accountStatisticRequestDto));
    }

    @Override
    public ResponseEntity<CommentStatisticResponseDto> getCommentStatistic(CommentStatisticRequestDto commentStatisticRequestDto) {
        log.info("comment get statistic");
        return ResponseEntity.ok(statisticService.getCommentStatistic(commentStatisticRequestDto));
    }

    @Override
    public ResponseEntity<LikeStatisticResponseDto> getLikeStatistic(LikeStatisticRequestDto likeStatisticRequestDto) {
        log.info("like get statistic");
        return ResponseEntity.ok(statisticService.getLikeStatistic(likeStatisticRequestDto));
    }

    @Override
    public ResponseEntity<PostStatisticResponseDto> getPostStatistic(PostStatisticRequestDto postStatisticRequestDto) {
        log.info("post get statistic");
        return ResponseEntity.ok(statisticService.getPostStatistic(postStatisticRequestDto));
    }

}
