package com.tust.app.services.impl;

import com.tust.app.dao.VideoMapper;
import com.tust.app.domain.Video;
import com.tust.app.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;
    @Override
    public List<Video> videoList() {
        return videoMapper.selectAllVideo();
    }

    @Override
    public boolean deleteVideoById(Integer id) {
        return videoMapper.deleteVideoById(id);
    }

    @Override
    public  Video selectVideoById(Integer id) {
        return videoMapper.selectVideoById(id);
    }

    @Override
    public int addVideo(Video video) {
        return videoMapper.addVideo(video);
    }
}
