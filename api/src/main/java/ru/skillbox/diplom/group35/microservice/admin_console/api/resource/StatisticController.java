package ru.skillbox.diplom.group35.microservice.admin_console.api.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account.AccountStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.account.AccountStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.comment.CommentStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.comment.CommentStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.like.LikeStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.like.LikeStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.post.PostStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.post.PostStatisticResponseDto;

import java.time.ZonedDateTime;

@RequestMapping("/api/v1/admin-console")
public interface StatisticController {

    @GetMapping("/statistic/account")
    ResponseEntity<AccountStatisticResponseDto> getAccountStatistic(AccountStatisticRequestDto accountStatisticRequestDto);

    @GetMapping("/statistic/comment")
    ResponseEntity<CommentStatisticResponseDto> getCommentStatistic(CommentStatisticRequestDto commentStatisticRequestDto);

    @GetMapping("/statistic/like")
    ResponseEntity<LikeStatisticResponseDto> getLikeStatistic(LikeStatisticRequestDto likeStatisticRequestDto);

    @GetMapping("/statistic/post")
    ResponseEntity<PostStatisticResponseDto> getPostStatistic(PostStatisticRequestDto postStatisticRequestDto);

}
