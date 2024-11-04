package com.tust.app.dao;

import com.tust.app.domain.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface NewsMapper {

    List<News> selectAllNews();
    News selectNewsById(Integer id);
    List<News> selectNewsByTitle(@Param("title") String title);

    int writeNews(News news);
    boolean deleteNewsById(Integer id);
    boolean updateNews(News news);


}
