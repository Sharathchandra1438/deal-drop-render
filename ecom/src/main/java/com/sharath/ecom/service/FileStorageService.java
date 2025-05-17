package com.sharath.ecom.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FileStorageService {

    private static final Logger logger = Logger.getLogger(FileStorageService.class.getName());

    private final Path fileStorageLocation;

    public FileStorageService() {
        this.fileStorageLocation = Paths.get("uploads")
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
            logger.log(Level.INFO, "Upload directory created or exists: " + this.fileStorageLocation);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Could not create upload directory", ex);
            throw new RuntimeException("Could not create upload directory", ex);
        }
    }

    public String storeFile(MultipartFile file) throws IOException {
        if (!Files.exists(fileStorageLocation)) {
            Files.createDirectories(fileStorageLocation);  // Ensure directory exists
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        logger.log(Level.INFO, "File stored at: " + targetLocation);
        return "/uploads/" + fileName;
    }
}