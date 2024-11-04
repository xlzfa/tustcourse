package com.tust.app.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class DownController {

    private final Path fileStorageLocation;

    public DownController() {
        // 使用绝对路径
        this.fileStorageLocation = Paths.get("D:/Programming/Java/java_program/tust/src/main/webapp/static/file/").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @GetMapping("/down")
    public ResponseEntity<Resource> downloadFile(@RequestParam String filename) {
        System.out.println("Requested filename: " + filename);
        System.out.println("File storage location: " + this.fileStorageLocation.toString());
        try {
            // 解析文件路径
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
            System.out.println("Resolved file path: " + filePath.toString());
            // 加载文件资源
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                // 探测文件内容类型
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                // 构建并返回响应
                return ResponseEntity.ok()
                        .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                // 如果文件不存在或不可读，则抛出运行时异常
                throw new RuntimeException("File not found " + filename);
            }
        } catch (Exception ex) {
            // 处理异常并抛出运行时异常
            throw new RuntimeException("File not found " + filename, ex);
        }
    }




}
