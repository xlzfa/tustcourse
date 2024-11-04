package com.tust.app.services.impl;

import com.tust.app.dao.QTopicMapper;
import com.tust.app.domain.QTopic;
import com.tust.app.services.QTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QTopicServiceImpl implements QTopicService {

    @Autowired
    private QTopicMapper qTopicMapper;

    @Override
    public void addTopic(QTopic topic) {
        qTopicMapper.insertTopic(topic);
    }

    @Override
    public boolean deleteTopic(int id) {
        try {
            qTopicMapper.deleteTopic(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void modifyTopic(QTopic topic) {
        qTopicMapper.updateTopic(topic);
    }

    @Override
    public QTopic getTopicById(int id) {
        return qTopicMapper.selectTopicById(id);
    }

    @Override
    public List<QTopic> getAllTopics() {
        return qTopicMapper.selectAllTopics();
    }
    @Override
    public List<QTopic> getTopicsByUsername(String username) {
        return qTopicMapper.selectTopicsByUsername(username);
    }
}
