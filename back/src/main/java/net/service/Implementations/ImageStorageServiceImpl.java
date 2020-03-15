package net.service.Implementations;

import net.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {
    @Value("${upload.path}")
    private String uploadPath;

    @Value("${host}")
    private String host;

    @Value("${images.path}")
    private String imagesPath;

    @Override
    public String save(MultipartFile file) {
        if (file.isEmpty())
            return null;
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains(".."))
            return null;

        fileName = UUID.randomUUID().toString() + "." + fileName;

        try {
            file.transferTo(new File(uploadPath + "/" + fileName));
            return host + "/" + imagesPath + "/" + fileName;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void delete(String fileName) {
        fileName = StringUtils.cleanPath(fileName);
        if (fileName.contains(".."))
            return;
        try {
            Files.deleteIfExists(Paths.get(uploadPath + "/" + fileName));
        } catch (IOException e) {
        }
    }

    @Override
    public Resource getFile(String fileName) {
        fileName = StringUtils.cleanPath(fileName);
        try {
            Resource resource = new UrlResource("file://" + uploadPath + "/" + fileName);
            if (resource.exists() && resource.isReadable())
                return resource;
            else
                return null;
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
