package com.tust.app.services;

import com.tust.app.domain.Answers;

import java.util.List;

public interface AnswersService {

    public List<Answers> selectAllAnswer();

    public Answers selectById(Integer id);

    public boolean updateAnswerState(Answers answers);

    public boolean deleteAnswer(Integer id);

    public boolean addAnswer(Answers answers);

}
