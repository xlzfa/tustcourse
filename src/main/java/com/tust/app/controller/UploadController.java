package com.tust.app.controller;
import com.tust.app.dao.SourceMapper;
import com.tust.app.domain.Video;
import com.tust.app.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UploadController {

    @Autowired
    private VideoService videoService;

    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //文件上传功能
        //原始文件位置
        String rootPath = request.getSession().getServletContext().getRealPath("/static/file/");
        //原始名称
        String originalFileName = file.getOriginalFilename();
        //新文件
        File newFile = new File(rootPath + File.separator + File.separator + originalFileName);
        //判断目标文件所在目录是否存在
        if (!newFile.getParentFile().exists()) {
            // 如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }
        System.out.println(newFile);
        // 将内存中的数据写入磁盘
        file.transferTo(newFile);
        return "forward:add";
    }


//    @Autowired
//    private VideoService videoService;
//    @PostMapping("videoUpload")
//    @ResponseBody
//    public ModelAndView VideoUpload(@RequestParam(value = "file",required = false) MultipartHttpServletRequest request, ModelMap map){
//    String message = " ";
//        Video video = new Video();
//        String logoPathDir = request.Parameter("shipin");
//    }





    private final ServletContext servletContext;

    public UploadController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping("/result")
    public String showResult(){
        return "admin/movie/result";
    }


    @PostMapping("/videoUpload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "请选择一个文件进行上传。");
            return "redirect:/uploadStatus";
        }

        try {
            // 获取上传文件的保存路径，这里假设存储在项目的 webapp/static/videos 目录下
            String uploadDir = servletContext.getRealPath("/static/video/");
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs(); // 创建目录
            }

            // 保存文件到指定路径
            String filePath = uploadDir + file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(bytes);
            stream.close();

            Video video = new Video();
            String filename = file.getOriginalFilename();
            int dotIndex = filename.lastIndexOf('.');
            video.setTitleOrig(filename.substring(0, dotIndex));
            //video.setTitleAlter();
            video.setSize(formatFileSize(file.getSize()));
            video.setType(filename.substring(dotIndex));
            video.setPath("static/video/" + file.getOriginalFilename());

            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String format1 = format.format(date);
            video.setUploadTime(format1);

            int i = videoService.addVideo(video);

            redirectAttributes.addFlashAttribute("result", video);
            redirectAttributes.addFlashAttribute("message", "文件上传成功: " + file.getOriginalFilename());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "文件上传失败: " + file.getOriginalFilename());
            e.printStackTrace();
        }

        return "redirect:/result";
    }


    public static String formatFileSize(long sizeInBytes) {
        final long KILOBYTE = 1024;
        final long MEGABYTE = KILOBYTE * 1024;
        final long GIGABYTE = MEGABYTE * 1024;

        if (sizeInBytes < KILOBYTE) {
            return sizeInBytes + " B";
        } else if (sizeInBytes < MEGABYTE) {
            return String.format("%.2f KB", (double) sizeInBytes / KILOBYTE);
        } else if (sizeInBytes < GIGABYTE) {
            return String.format("%.2f MB", (double) sizeInBytes / MEGABYTE);
        } else {
            return String.format("%.2f GB", (double) sizeInBytes / GIGABYTE);
        }
    }


}
