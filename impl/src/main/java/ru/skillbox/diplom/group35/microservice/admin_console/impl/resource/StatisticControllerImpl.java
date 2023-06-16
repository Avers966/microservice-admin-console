package ru.skillbox.diplom.group35.microservice.admin_console.impl.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.diplom.group35.library.core.annotation.EnableExceptionHandler;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.account.api.dto.AccountStatisticResponseDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.resource.StatisticController;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.service.statistic.StatisticService;
import ru.skillbox.diplom.group35.microservice.post.dto.statistic.PostStatisticRequestDto;
import ru.skillbox.diplom.group35.microservice.post.dto.statistic.StatisticResponseDto;

@Slf4j
@RestController
@EnableExceptionHandler
@RequiredArgsConstructor
public class StatisticControllerImpl implements StatisticController {

    private final StatisticService statisticService;

    @Override
    public ResponseEntity<AccountStatisticResponseDto> getAccountStatistic(AccountStatisticRequestDto accountStatisticRequestDto) {
        log.info("get account statistic");
        return ResponseEntity.ok(statisticService.getAccountStatistic(accountStatisticRequestDto));
    }

    @Override
    public ResponseEntity<StatisticResponseDto> getCommentStatistic(PostStatisticRequestDto postStatisticRequestDto) {
        log.info("get comment statistic");
        return ResponseEntity.ok(statisticService.getCommentStatistic(postStatisticRequestDto));
    }

    @Override
    public ResponseEntity<StatisticResponseDto> getLikeStatistic(PostStatisticRequestDto postStatisticRequestDto) {
        log.info("get like statistic");
        return ResponseEntity.ok(statisticService.getLikeStatistic(postStatisticRequestDto));
    }

    @Override
    public ResponseEntity<StatisticResponseDto> getPostStatistic(PostStatisticRequestDto postStatisticRequestDto) {
        log.info("get post statistic");
        return ResponseEntity.ok(statisticService.getPostStatistic(postStatisticRequestDto));
    }

}
