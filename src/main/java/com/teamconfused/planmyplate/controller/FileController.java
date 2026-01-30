package com.teamconfused.planmyplate.controller;

import com.teamconfused.planmyplate.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

  private final FileStorageService storageService;

  @Value("${upload.path}")
  private String uploadPath;

  @PostMapping("/upload")
  public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
    String filename = storageService.save(file);

    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/" + uploadPath + "/")
        .path(filename)
        .toUriString();

    return ResponseEntity.ok(Map.of(
        "url", fileDownloadUri,
        "filename", filename));
  }
}
