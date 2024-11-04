package com.tust.app.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tust.app.domain.News;
import com.tust.app.services.NewsService;
import com.tust.app.services.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/page")
    public String page(HttpServletRequest request){

        //在数据库中查询，丢给页面

        PageHelper.startPage(1,3);


        List<News> news = newsService.getAllNews();
        PageInfo page = new PageInfo(news);

        //数据传到页面上去
        request.setAttribute("pageInfo",page);

        return "home";
    }


    @RequestMapping("/page/{pageNum}")
    public String page2(@PathVariable("pageNum") Integer pageNum, HttpServletRequest request){

        //在数据库中查询，丢给页面

        PageHelper.startPage(pageNum,3);


        List<News> news = newsService.getAllNews();
        PageInfo page = new PageInfo(news);

        //数据传到页面上去
        request.setAttribute("pageInfo",page);

        return "home";
    }


    @RequestMapping("/anews")
    public String anews(Integer pn, HttpServletRequest request){

        if(pn == null){
            pn = 1;
        }
        //在数据库中查询，丢给页面
        PageHelper.startPage(pn,3);
        List<News> news = newsService.getAllNews();
        PageInfo page = new PageInfo(news);
        //数据传到页面上去
        request.setAttribute("news",page);
        return "admin/news/adminPageList";
    }

//    @RequestMapping("/anews/{pn}")
//    public String anews(@PathVariable("pn") Integer pageNum, HttpServletRequest request){
//        //在数据库中查询，丢给页面
//        PageHelper.startPage(pageNum,3);
//        List<News> news = newsService.getAllNews();
//        PageInfo page = new PageInfo(news);
//        //数据传到页面上去
//        request.setAttribute("news",page);
//        return "admin/news/adminPageList";
//    }

    @RequestMapping("selectByTitle")
    public String selectByTitle(String title, HttpServletRequest request){
        //根据title去查询相关的文章
        PageHelper.startPage(1,8);
        List<News> searchList = newsService.search(title);
        PageInfo pageInfo = new PageInfo(searchList);
        request.setAttribute("news",pageInfo);//为什么是news？
        if(searchList != null){
            return "admin/news/adminPageList";
        }
        return "error";
    }

    @RequestMapping("/writeNews")
    public String writeNews(String title, String article, HttpServletRequest request){

        //构造一个news对象
        News news = new News();
        news.setTitle(title);
        news.setArticle(article);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        news.setTime(format1);

        Integer i = newsService.addArticle(news);
        if (i > 0){
            //查询所有的文章，传递给页面
            PageHelper.startPage(1,10);
            List<News> news1 = newsService.getAllNews();
            PageInfo pageInfo = new PageInfo(news1);
            request.setAttribute("news", pageInfo);
            return "admin/news/adminPageList";
        }
        return "error";
    }

    @RequestMapping("/getNews")
    public String getNews(Integer id, HttpServletRequest request){

        //根据id获取新闻信息
        News newsById = newsService.findNewsById(id);
        //查出来的对象传递到页面
        request.setAttribute("news",newsById);

        return "admin/news/editNews";
    }

    @RequestMapping("updateNews")
    public String updateNews(Integer id, String title, String article){

        News news = new News();
        news.setId(id);
        news.setTitle(title);
        news.setArticle(article);
        Boolean aBoolean = newsService.modifyNews(news);

        if(aBoolean){
            return "forward:/anews";
        }else {
            return "error";
        }

    }

    @RequestMapping("deleteNewsById")
    public String deleteNewsById(Integer id){

        Boolean b = newsService.delNews(id);

        if(b){
            return "forward:/anews";
        }else {
            return "error";
        }

    }

    @RequestMapping("selectNewsById")
    public String selectNewsById(Integer id, HttpServletRequest request){

        //根据id查询一条新闻消息
        News newsById = newsService.findNewsById(id);
        request.setAttribute("news",newsById);
        return "detail";

    }

}
