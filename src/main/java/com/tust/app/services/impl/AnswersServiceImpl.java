package com.tust.app.services.impl;

import com.tust.app.dao.AnswersMapper;
import com.tust.app.domain.Answers;
import com.tust.app.services.AnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnswersServiceImpl implements AnswersService {

    @Autowired
    private AnswersMapper answersMapper;

    @Override
    public List<Answers> selectAllAnswer() {
        return answersMapper.selectAllAnswer();
    }

    @Override
    public Answers selectById(Integer id) {
        return answersMapper.selectById(id);
    }


    @Override
    public boolean updateAnswerState(Answers answers) {
        return answersMapper.updateAnswerState(answers);
    }

    @Override
    public boolean deleteAnswer(Integer id) {
        return answersMapper.deleteAnswer(id);
    }

    @Override
    public boolean addAnswer(Answers answers) {
        return answersMapper.addAnswer(answers);
    }


}
