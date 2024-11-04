package com.tust.app.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tust.app.domain.QTopic;
import com.tust.app.services.QTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class QTopicController {

    @Autowired
    private QTopicService qTopicService;


    @GetMapping("/selectAllTopic")
    public String selectAllTopic(@RequestParam(defaultValue = "1") int pn, HttpServletRequest request, HttpSession session) {
        String username = (String) session.getAttribute("username"); // 从session中获取用户名
        PageHelper.startPage(pn, 10);
        List<QTopic> topics = qTopicService.getTopicsByUsername(username); // 获取该用户的帖子列表
        PageInfo<QTopic> pageInfo = new PageInfo<>(topics);
        request.setAttribute("topicList", pageInfo);
        return "frontPage/topicList"; // 确保这是你的 JSP 页面名称
    }


    @RequestMapping("/deleteTopic")
    public String deleteTopic(@RequestParam("id") Integer id) {
        boolean success = qTopicService.deleteTopic(id);
        if (success) {
            return "forward:selectAllTopic";
        } else {
            return "error";
        }
    }

    @GetMapping("/selectTopicById")
    public String selectTopicById(@RequestParam("id") int id, HttpServletRequest request) {
        QTopic topic = qTopicService.getTopicById(id);
        request.setAttribute("topic", topic);
        return "frontPage/edittopic";
    }

    @PostMapping("/editTopic")
    public String editTopic(QTopic topic, HttpSession session) {
        topic.setState(1);
        String username = (String) session.getAttribute("username");
        topic.setEdituser(username);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        topic.setPubtime(format1);

        qTopicService.modifyTopic(topic);
        return "redirect:/selectAllTopic";
    }
}