package com.tust.app.dao;

import com.tust.app.domain.QTopic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface QTopicMapper {
    void insertTopic(QTopic topic);
    void deleteTopic(@Param("id") int id);
    void updateTopic(QTopic topic);
    QTopic selectTopicById(@Param("id") int id);
    List<QTopic> selectAllTopics();
    List<QTopic> selectTopicsByUsername(String username);


}
