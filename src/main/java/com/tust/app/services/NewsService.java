package com.tust.app.services;

import com.tust.app.domain.News;

import java.util.List;

public interface NewsService {

    public List<News> getAllNews();

    //文章搜索
    List<News> search(String title);

    Integer addArticle(News news);

    News findNewsById(Integer id);

    Boolean modifyNews(News news);

    Boolean delNews(Integer id);


}
