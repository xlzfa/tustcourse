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

        //�ļ��ϴ�����
        //ԭʼ�ļ�λ��
        String rootPath = request.getSession().getServletContext().getRealPath("/static/file/");
        //ԭʼ����
        String originalFileName = file.getOriginalFilename();
        //���ļ�
        File newFile = new File(rootPath + File.separator + File.separator + originalFileName);
        //�ж�Ŀ���ļ�����Ŀ¼�Ƿ����
        if (!newFile.getParentFile().exists()) {
            // ���Ŀ���ļ����ڵ�Ŀ¼�����ڣ��򴴽���Ŀ¼
            newFile.getParentFile().mkdirs();
        }
        System.out.println(newFile);
        // ���ڴ��е�����д�����
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
            redirectAttributes.addFlashAttribute("message", "��ѡ��һ���ļ������ϴ���");
            return "redirect:/uploadStatus";
        }

        try {
            // ��ȡ�ϴ��ļ��ı���·�����������洢����Ŀ�� webapp/static/videos Ŀ¼��
            String uploadDir = servletContext.getRealPath("/static/video/");
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs(); // ����Ŀ¼
            }

            // �����ļ���ָ��·��
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
            redirectAttributes.addFlashAttribute("message", "�ļ��ϴ��ɹ�: " + file.getOriginalFilename());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "�ļ��ϴ�ʧ��: " + file.getOriginalFilename());
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
