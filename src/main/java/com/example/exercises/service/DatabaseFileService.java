package com.example.exercises.service;

import java.io.IOException;

import com.example.exercises.domain.model.DatabaseFile;
import com.example.exercises.domain.repository.DatabaseFileRepository;
import com.example.exercises.exception.FileStorageException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DatabaseFileService {

    private final DatabaseFileRepository dbFileRepository;

    public DatabaseFileService(DatabaseFileRepository dbFileRepository) {
        this.dbFileRepository = dbFileRepository;
    }

    public DatabaseFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DatabaseFile dbFile = new DatabaseFile(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DatabaseFile getFile(Integer fileId) {
        return dbFileRepository.findById(fileId).orElseThrow();
    }
}