package ru.skillbox.diplom.group35.microservice.admin_console.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.storage.StorageDto;

import javax.validation.constraints.Size;

/**
 * StorageController
 *
 * @author Georgii Vinogradov
 */

@RequestMapping("/api/v1/storage")
public interface StorageController {
    @PostMapping
    ResponseEntity<StorageDto> upload(@RequestParam("file") MultipartFile file);
}
