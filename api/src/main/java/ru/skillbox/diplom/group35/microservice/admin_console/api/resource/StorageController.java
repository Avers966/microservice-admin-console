package ru.skillbox.diplom.group35.microservice.admin_console.api.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
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

@SecurityRequirement(name = "JWT")
@Tag(name = "Storage service", description = "Работа с файлами")
@ApiResponse(responseCode = "200", description = "Successful operation")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
@RequestMapping(value = "/api/v1/storage",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public interface StorageController {
    @PostMapping
    @Operation(description = "Получение ссылки на загруженный файл")
    ResponseEntity<StorageDto> upload(@RequestParam("file") MultipartFile file);
}
