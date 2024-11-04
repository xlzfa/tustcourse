package com.tust.app.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class VideoDownController {

    private final ServletContext servletContext;

    public VideoDownController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadVideo(@RequestParam("filename") String filename) throws IOException {
        // 获取文件存储路径
        String uploadDir = servletContext.getRealPath("/");
        File file = new File(uploadDir + filename);

        if (!file.exists()) {
            System.out.println("文件未找到: " + file.getAbsolutePath());
            return ResponseEntity.notFound().build();
        }

        System.out.println("文件路径: " + file.getAbsolutePath());

        // 确定文件的媒体类型
        MediaType mediaType = getMediaTypeForFileName(this.servletContext, file.getName());

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }

    private MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        // 根据文件扩展名确定媒体类型
        String mimeType = servletContext.getMimeType(fileName);
        try {
            return MediaType.parseMediaType(mimeType);
        } catch (Exception e) {
            // 默认媒体类型
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}

