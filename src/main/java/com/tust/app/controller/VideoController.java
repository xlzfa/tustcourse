package com.tust.app.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tust.app.domain.News;
import com.tust.app.domain.Source;
import com.tust.app.domain.Video;
import com.tust.app.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;


    @RequestMapping("getAllVideo")
    public String getAllVideo(Integer pn, HttpServletRequest request) {
        if (pn == null) {
            pn = 1;
        }
        PageHelper.startPage(pn, 3);
        List<Video> videos = videoService.videoList();
        PageInfo page = new PageInfo(videos);
        request.setAttribute("videoList", page);
        return "frontPage/allVideo";
    }

    @RequestMapping("getAllVideo2")
    public String getAllVideo2(Integer pn, HttpServletRequest request) {
        if (pn == null) {
            pn = 1;
        }
        PageHelper.startPage(pn, 3);
        List<Video> videos = videoService.videoList();
        PageInfo page = new PageInfo(videos);
        request.setAttribute("videoList", page);
        return "admin/movie/allVideo";
    }

    @RequestMapping("deleteVideoById")
    public String deleteVideoById(Integer id) {
        boolean b = videoService.deleteVideoById(id);
        if (b) {
            return "forward:/getAllVideo2";
        }
        return "error";
    }

    @GetMapping("/play")
    public String playVideo(@RequestParam("id") Integer id, HttpServletRequest request) {
        Video video = videoService.selectVideoById(id);
        if (video != null) {
            String videoPath = "static/videos/" + video.getPath();
            System.out.println("Video Path: " + videoPath);
            request.setAttribute("name", video.getTitleOrig());
            request.setAttribute("path", "/" + video.getPath());

            return "frontPage/playVideo";
        } else {
            request.setAttribute("error", "视频未找到");
            return "error";
        }
    }

//    @RequestMapping("addVideo")
//    public String addVideo(MultipartFile file,HttpServletRequest request){
//
//        Video video = new Video();
//        String filename = file.getOriginalFilename();
//        int dotIndex = filename.lastIndexOf('.');
//        video.setTitleOrig(filename.substring(0, dotIndex));
//        //video.setTitleAlter();
//        video.setSize(formatFileSize(file.getSize()));
//        video.setType(filename.substring(dotIndex));
//        video.setPath("static/video/" + file.getOriginalFilename());
//
//
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String format1 = format.format(date);
//        video.setUploadTime(format1);
//
//        Integer i = videoService.addVideo(video);
//        if (i > 0){
//            //查询所有的文章，传递给页面
//            PageHelper.startPage(1,10);
//            List<Video> video1 = videoService.videoList();
//            PageInfo pageInfo = new PageInfo(video1);
//            request.setAttribute("sourceList", pageInfo);
//            return "forward:getAllVideo2";
//        }
//        return "error";
//    }
//
//    // 辅助方法：将文件大小转换为可读格式
//    public static String formatFileSize(long sizeInBytes) {
//        final long KILOBYTE = 1024;
//        final long MEGABYTE = KILOBYTE * 1024;
//        final long GIGABYTE = MEGABYTE * 1024;
//
//        if (sizeInBytes < KILOBYTE) {
//            return sizeInBytes + " B";
//        } else if (sizeInBytes < MEGABYTE) {
//            return String.format("%.2f KB", (double) sizeInBytes / KILOBYTE);
//        } else if (sizeInBytes < GIGABYTE) {
//            return String.format("%.2f MB", (double) sizeInBytes / MEGABYTE);
//        } else {
//            return String.format("%.2f GB", (double) sizeInBytes / GIGABYTE);
//        }
//    }

//    @RequestMapping("/uploadResult/{id}")
//    public String selectVideoById(@PathVariable("id") Integer id, HttpServletRequest request){
//        Video video = videoService.selectVideoById(id);
//        request.setAttribute("entity",video);
//        return "result";
//    }

}