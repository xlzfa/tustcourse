package com.tust.app.dao;

import com.tust.app.domain.Topic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TopicMapper {

    public List<Topic> selectAllTopic3();
    public Topic selectTopicById(int id);
    public boolean updateState(Topic topic);
    public boolean deleteTopic(Integer id);
    public boolean addTopic(Topic topic);



}
