package com.tust.app.services.impl;


import com.tust.app.dao.NewsMapper;
import com.tust.app.domain.News;
import com.tust.app.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> getAllNews(){

        List<News> newsList = newsMapper.selectAllNews();

        return newsList;
    }

    @Override
    public List<News> search(String title){
        if(title != null && title.length() > 0){
            return newsMapper.selectNewsByTitle(title);
        }else {
            return null;
        }
    }

    @Override
    public Integer addArticle(News news) {
        return newsMapper.writeNews(news);
    }

    @Override
    public News findNewsById(Integer id) {
        return newsMapper.selectNewsById(id);
    }

    @Override
    public Boolean modifyNews(News news) {
        return newsMapper.updateNews(news);
    }

    @Override
    public Boolean delNews(Integer id) {
        return newsMapper.deleteNewsById(id);
    }


}
