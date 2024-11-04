package com.tust.app.services.impl;

import com.tust.app.dao.TopicMapper;
import com.tust.app.domain.Topic;
import com.tust.app.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicMapper topicMapper;
    @Override
    public List<Topic> selectAllTopic3() {
        return topicMapper.selectAllTopic3();
    }

    @Override
    public Topic selectTopicById(Integer id) {
        return topicMapper.selectTopicById(id);
    }

    @Override
    public boolean updateState(Topic topic) {
        return topicMapper.updateState(topic);
    }

    @Override
    public boolean deleteTopic(Integer id) {
        return topicMapper.deleteTopic(id);
    }

    @Override
    public boolean addTopic(Topic topic) {
        return topicMapper.addTopic(topic);
    }

}
