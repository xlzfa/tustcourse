package com.tust.app.controller;

import com.tust.app.domain.Users;
import com.tust.app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session) {

        Integer id = usersService.selectIdByUsername(username);
        Users user = usersService.searchUserById(id);


        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("username", username);
            if(user.getRoleId() == 1){
                return "redirect:/admin/home.jsp";
            }else {
                return "redirect:/frontPage/home.jsp";
            }
        } else {
            return "error";
        }
    }

    @RequestMapping("adlogout")
    public String adlogOut(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        return "forward:./page";
    }

    @RequestMapping("logout")
    public String logOut(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        return "forward:/page";
    }


    @PostMapping("/changeKey")
    public String changePassword(@RequestParam String password, HttpSession session) {
        // 从session中获取username
        String username = (String) session.getAttribute("username");

        Integer id = usersService.selectIdByUsername(username);
        Users user = usersService.searchUserById(id);

        user.setPassword(password);
        Boolean b = usersService.modifyUsers(user);

        if (b){
            return "redirect:/admin/home.jsp";
        }else {
            return "error";
        }

    }

    @PostMapping("/update")
    public String changeUserPassword(@RequestParam String password,
                                     @RequestParam String email,
                                     HttpSession session) {
        // 从session中获取username
        String username = (String) session.getAttribute("username");

        Integer id = usersService.selectIdByUsername(username);
        Users user = usersService.searchUserById(id);

        user.setPassword(password);
        user.setEmail(email);
        Boolean b = usersService.modifyUsers(user);

        if (b){
            return "redirect:/frontPage/home.jsp";
        }else {
            return "error";
        }

    }


}
