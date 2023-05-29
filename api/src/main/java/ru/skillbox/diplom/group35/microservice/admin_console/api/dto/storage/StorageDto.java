package ru.skillbox.diplom.group35.microservice.admin_console.api.dto.storage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * StorageDto
 *
 * @author Georgii Vinogradov
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dto загруженного файла")
public class StorageDto {
    @Schema(description = "Ссылка на файл в облаке")
    private String fileName;
}
