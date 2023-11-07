package by.krainet.matveenko.serviceforcv.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private final String uploadDir = "./uploads";

    public String getUploadDir() {
        return uploadDir;
    }
}