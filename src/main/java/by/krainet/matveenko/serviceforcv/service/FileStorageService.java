package by.krainet.matveenko.serviceforcv.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String storeFile(MultipartFile file, String name);

    Resource loadFileAsResource(String fileName);
}