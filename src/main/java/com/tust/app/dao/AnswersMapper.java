package com.tust.app.dao;

import com.tust.app.domain.Answers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswersMapper {
    List<Answers> selectAllAnswer();
    Answers selectById(Integer id);
    boolean updateAnswerState(Answers answers);
    boolean deleteAnswer(Integer id);
    boolean addAnswer(Answers answers);

}
