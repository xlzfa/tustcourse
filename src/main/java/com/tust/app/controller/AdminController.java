package com.tust.app.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tust.app.domain.News;
import com.tust.app.domain.Users;
import com.tust.app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/adminLogin")
    public String adLogin(){

        return "redirect:admin/home.jsp";
    }


    @RequestMapping("/getAllUser")
    public String allUser(Integer pn, HttpServletRequest request){

        if(pn == null){
            pn = 1;
        }
        PageHelper.startPage(pn,3);
        List<Users> allUsers = usersService.getAllUsers();
        PageInfo pageInfo = new PageInfo(allUsers);

        request.setAttribute("userList",pageInfo);
        return "admin/user/allUser";
    }


    @RequestMapping("/deleteUser")
    public String delUserById(Integer id){

        Boolean b = usersService.delUser(id);

        if(b){
            return "forward:/getAllUser";
        }else {
            return "error";
        }

    }


    @RequestMapping("/selectByName")
    public String selectByName(String username, HttpServletRequest request){

        PageHelper.startPage(1,8);
        List<Users> searchList = usersService.search(username);
        PageInfo pageInfo = new PageInfo(searchList);
        request.setAttribute("userList",pageInfo);

        if(searchList != null){
            return "admin/user/allUser";
        }
        return "error";
    }


    //	<li><a href="${APP_PATH}/changeRole?username=${user.username}">更改用户分组</a></li>
    //	<li><a href="${APP_PATH}/changeState?username=${user.username}">更改用户状态</a></li>

    @RequestMapping("/changeRole")
    public String changeRole(Integer id){

        Users user = usersService.searchUserById(id);
        if(user.getRoleId() == 1){
            user.setRoleId(2);
        }else {
            user.setRoleId(1);
        }
        if(usersService.modifyUsers(user)){
            return "forward:/getAllUser";
        }else {
            return "error";
        }

    }

    @RequestMapping("/changeState")
    public String changeState(Integer id){
        Users user = usersService.searchUserById(id);
        if(user.getState() == 1){
            user.setState(2);
        }else {
            user.setState(1);
        }
        if(usersService.modifyUsers(user)){
            return "forward:/getAllUser";
        }else {
            return "error";
        }
    }





}

