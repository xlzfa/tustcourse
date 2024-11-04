package com.tust.app.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor


public class News {

    private Integer id;
    private String title;
    private String article;
    private String time;

}
