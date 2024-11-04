package com.tust.app.domain;


import lombok.Data;

@Data
public class Video {
    private Integer id;
    private String titleOrig;
    private String titleAlter;
    private String size;
    private String type;
    private String path;
    private String uploadTime;



}
