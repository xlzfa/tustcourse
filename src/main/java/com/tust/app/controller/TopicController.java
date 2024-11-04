package com.tust.app.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tust.app.domain.Answers;
import com.tust.app.domain.Topic;
import com.tust.app.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TopicController {
    @Autowired
    private TopicService topicService;

    @RequestMapping("/selectAllTopic3")
    public String selectAllTopic3(HttpServletRequest request){

        PageHelper.startPage(1, 8);
        List<Topic> topics = topicService.selectAllTopic3();
        PageInfo pageInfo = new PageInfo(topics);
        request.setAttribute("topicLists", pageInfo);
        if (topics != null){
            return "/admin/bbs/allTopic";
        }
        return "error";

    }


    @RequestMapping("/selectAllTopic2")
    public String selectAllTopic2(HttpServletRequest request){

        PageHelper.startPage(1, 8);
        List<Topic> topics = topicService.selectAllTopic3();
//        System.out.println(topics);
        PageInfo pageInfo = new PageInfo(topics);
        request.setAttribute("topicLists", pageInfo);
        if (topics != null){
            return "frontPage/messageList";
        }
        return "error";

    }


    @RequestMapping("/updateState")
    public String updateState(Integer id){


        Topic topic = topicService.selectTopicById(id);

        if (topic.getState()==1){
            topic.setState(0);
        }
        else {
            topic.setState(1);
        }
        boolean b = topicService.updateState(topic);
        if (b){
            return "forward:selectAllTopic3";
        }
        return "error";

    }

    @RequestMapping("/selectTopicAnswerById")
    public String selectTopicAnswerById(Integer id,HttpServletRequest request){


        Topic topic = topicService.selectTopicById(id);
        request.setAttribute("list",topic);
        request.setAttribute("replyNum",1);
        return "frontPage/detail";
        }




    @RequestMapping("/deleteTopic2")
    public String deleteTopic(Integer id) {

        boolean b = topicService.deleteTopic(id);
        if (b){
            return "forward:selectAllTopic3";
        }
        return "error";
    }

    @RequestMapping("/addTopic")
    public String addTopic(Topic topic) {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        topic.setPubtime(format1);

        boolean b = topicService.addTopic(topic);
        if (b){
            return "forward:selectAllTopic3";
        }
        return "error";
    }

}
