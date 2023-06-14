package ru.skillbox.diplom.group35.microservice.admin_console.impl.service.storage;

import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.diplom.group35.library.core.exception.UploadImageException;
import ru.skillbox.diplom.group35.microservice.admin_console.api.dto.storage.StorageDto;
import ru.skillbox.diplom.group35.microservice.admin_console.impl.utils.CloudinaryUtil;

import javax.transaction.Transactional;


/**
 * StorageService
 *
 * @author Georgii Vinogradov
 */

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StorageService {

    private final CloudinaryUtil cloudinaryUtil;

    public StorageDto uploadFile(MultipartFile file) {
        String filename = StringUtils.stripFilenameExtension(file.getOriginalFilename());
        String url;
        try {
            url =  cloudinaryUtil.getConfig().uploader()
                    .upload(file.getBytes(), ObjectUtils.asMap("public_id", filename))
                    .get("url").toString();
        } catch (Exception exception) {
            throw new UploadImageException();
        }
        return new StorageDto(url);
    }
}
