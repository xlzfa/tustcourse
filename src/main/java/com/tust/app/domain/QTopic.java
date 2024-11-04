package com.tust.app.domain;

import lombok.Data;

@Data
public class QTopic {
    private int id;
    private String title;
    private String pubtime;
    private String detail;
    private String edituser;
    private int state;
}
