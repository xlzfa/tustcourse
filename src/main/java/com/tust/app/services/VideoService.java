package com.tust.app.services;

import com.tust.app.domain.Video;

import java.util.List;

public interface VideoService {
    public List<Video> videoList();

    boolean deleteVideoById(Integer id);

    Video selectVideoById(Integer id);

    int addVideo(Video video);
}
