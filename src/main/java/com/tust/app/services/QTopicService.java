package com.tust.app.services;

import com.tust.app.domain.QTopic;
import java.util.List;

public interface QTopicService {
    void addTopic(QTopic topic);
    boolean deleteTopic(int id);
    void modifyTopic(QTopic topic);
    QTopic getTopicById(int id);
    List<QTopic> getAllTopics();
    List<QTopic> getTopicsByUsername(String username);
}
