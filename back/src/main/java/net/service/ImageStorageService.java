package net.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
    String save(MultipartFile file);
    void delete(String fileName);
    Resource getFile(String fileName);
}
