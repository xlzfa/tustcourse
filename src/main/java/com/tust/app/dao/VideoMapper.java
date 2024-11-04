package com.tust.app.dao;

import com.tust.app.domain.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper {
    List<Video> selectAllVideo();

    boolean deleteVideoById(Integer id);

    Video selectVideoById(Integer id);

    int addVideo(Video video);


}
