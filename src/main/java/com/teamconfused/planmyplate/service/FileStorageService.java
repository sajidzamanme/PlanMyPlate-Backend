package com.teamconfused.planmyplate.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

  private final Path root;

  public FileStorageService(@Value("${upload.path}") String uploadPath) {
    this.root = Paths.get(uploadPath);
    try {
      Files.createDirectories(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  public String save(MultipartFile file) {
    try {
      String extension = getFileExtension(file.getOriginalFilename());
      String filename = UUID.randomUUID().toString() + extension;
      Files.copy(file.getInputStream(), this.root.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
      return filename;
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  private String getFileExtension(String fileName) {
    if (fileName == null)
      return "";
    int lastIndex = fileName.lastIndexOf('.');
    return (lastIndex == -1) ? "" : fileName.substring(lastIndex);
  }
}
