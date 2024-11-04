package com.tust.app.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Topic {
    private Integer id;
    private String title;
    private String pubtime;
    private String detail;
    private String edituser;
    private Integer state;

}
