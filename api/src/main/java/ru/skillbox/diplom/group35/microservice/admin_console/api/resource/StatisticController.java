package ru.skillbox.diplom.group35.microservice.admin_console.api.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.comment.CommentStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.comment.CommentStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.like.LikeStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.like.LikeStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.post.PostStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.statistic.post.PostStatisticResponseDto;

@SecurityRequirement(name = "JWT")
@Tag(name = "Statistic service", description = "Работа со статистикой")
@ApiResponse(responseCode = "200", description = "Successful operation")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
@RequestMapping(value = "/api/v1/admin-console", produces = MediaType.APPLICATION_JSON_VALUE)
public interface StatisticController {

    @GetMapping("/statistic/account")
    @Operation(description = "Получение статистики аккаунтов")
    ResponseEntity<AccountStatisticResponseDto> getAccountStatistic(AccountStatisticRequestDto accountStatisticRequestDto);

    @GetMapping("/statistic/comment")
    @Operation(description = "Получение статистики комментариев")
    ResponseEntity<CommentStatisticResponseDto> getCommentStatistic(CommentStatisticRequestDto commentStatisticRequestDto);

    @GetMapping("/statistic/like")
    @Operation(description = "Получение статистики лайков")
    ResponseEntity<LikeStatisticResponseDto> getLikeStatistic(LikeStatisticRequestDto likeStatisticRequestDto);

    @GetMapping("/statistic/post")
    @Operation(description = "Получение статистики постов")
    ResponseEntity<PostStatisticResponseDto> getPostStatistic(PostStatisticRequestDto postStatisticRequestDto);

}
