package com.tust.app.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tust.app.domain.Answers;
import com.tust.app.domain.Topic;
import com.tust.app.services.AnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AnswersController {
    @Autowired
    private AnswersService answersService;



    @RequestMapping("/selectAllAnswer")
    public String selectAllAnswer(HttpServletRequest request) {

        PageHelper.startPage(1, 8);
        List<Answers> answer = answersService.selectAllAnswer();
        PageInfo pageInfo = new PageInfo(answer);
        request.setAttribute("answerLists", pageInfo);
        if (answer != null){
            return "admin/bbs/allAnswers";
        }
        return "error";
    }

    @RequestMapping("/updateAnswerState")
    public String updateAnswerState(Integer id) {

        Answers answers = answersService.selectById(id);

        if (answers.getState2()==1){
            answers.setState2(0);
        }
        else {
            answers.setState2(1);
        }

        boolean b = answersService.updateAnswerState(answers);
        if (b){
            return "forward:selectAllAnswer";
        }
        return "error";
    }

    @RequestMapping("/deleteAnswer")
    public String deleteAnswer(Integer id) {

        boolean b = answersService.deleteAnswer(id);
        if (b){
            return "forward:selectAllAnswer";
        }
        return "error";
    }
    @RequestMapping("/addAnswer")
    public String addAnswer(Answers answers) {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        answers.setPubtime2(format1);


        boolean b = answersService.addAnswer(answers);
        if (b){
            return "forward:selectAllTopic2";
        }
        return "error";
    }




}
