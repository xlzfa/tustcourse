package com.tust.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Answers {

    private Integer id;
    private String content;
    private String answeruser;
    private String pubtime2;
    private Integer topicId;
    private Integer state2;
}
