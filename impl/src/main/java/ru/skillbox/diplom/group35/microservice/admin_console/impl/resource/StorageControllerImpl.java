package ru.skillbox.diplom.group35.microservice.admin_console.impl.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.diplom.group35.library.core.annotation.EnableExceptionHandler;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.storage.StorageDto;
import ru.skillbox.diplom.group35.microservice.admin_console.api.resource.StorageController;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.service.storage.StorageService;

/**
 * StorageControllerImpl
 *
 * @author Georgii Vinogradov
 */

@Slf4j
@RestController
@EnableExceptionHandler
@RequiredArgsConstructor
public class StorageControllerImpl implements StorageController {

    private final StorageService storageService;

    @Override
    public ResponseEntity<StorageDto> upload(MultipartFile file) {
        log.info("upload file, type: {}", file.getOriginalFilename());
        return ResponseEntity.ok(storageService.uploadFile(file));
    }
}
