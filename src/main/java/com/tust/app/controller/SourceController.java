package com.tust.app.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tust.app.domain.News;
import com.tust.app.domain.Source;
import com.tust.app.services.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SourceController {

    @Autowired
    private SourceService sourceService;
    @RequestMapping("getAllSource")
    public String getAllSource(Integer pn , HttpServletRequest request){
        if (pn == null){
            pn = 1;
        }
        PageHelper.startPage(pn,3);
        List<Source> source = sourceService.sourceList();
        PageInfo page = new PageInfo(source);
        request.setAttribute("sourceList",page);
        return "admin/source/allSource";
    }


    @RequestMapping("getAllSource2")
    public String getAllSource2(Integer pn , HttpServletRequest request){
        if (pn == null){
            pn = 1;
        }
        PageHelper.startPage(pn,3);
        List<Source> source = sourceService.sourceList();
        PageInfo page = new PageInfo(source);
        request.setAttribute("sourceList",page);
        return "frontPage/allSource";
    }
    @RequestMapping("ougetAllSrce")
    public String getAllSource( HttpServletRequest request){

        PageHelper.startPage(1,3);
        List<Source> source = sourceService.sourceList();
        PageInfo page = new PageInfo(source);
        request.setAttribute("sourceList",page);
        return "admin/source/allSource";
    }

    @RequestMapping("delete")
    public String deleteSourceById(Integer id){
        boolean b = sourceService.deleteSource(id);
        if(b){
            return "forward:/getAllSource";
        }
        return "error";
    }

    @RequestMapping("add")
    public  String addSource(MultipartFile file, HttpServletRequest request){

        Source source = new Source();
        source.setFilename(file.getOriginalFilename());


        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        source.setPubtime(format1);

        Integer i = sourceService.addSource(source);
        if (i > 0){
            //查询所有的文章，传递给页面
            PageHelper.startPage(1,10);
            List<Source> source1 = sourceService.sourceList();
            PageInfo pageInfo = new PageInfo(source1);
            request.setAttribute("sourceList", pageInfo);
            return "forward:getAllSource";
        }
        return "error";
    }
}

