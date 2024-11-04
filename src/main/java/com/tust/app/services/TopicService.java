package com.tust.app.services;

import com.tust.app.domain.Answers;
import com.tust.app.domain.Topic;

import java.util.List;

public interface TopicService {

    public List<Topic> selectAllTopic3();

    public Topic selectTopicById(Integer id);

    public boolean updateState(Topic topic);

    public boolean deleteTopic(Integer id);

    public boolean addTopic(Topic topic);
}
