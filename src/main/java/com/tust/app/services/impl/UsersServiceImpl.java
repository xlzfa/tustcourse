package com.tust.app.services.impl;

import com.tust.app.dao.UserMapper;
import com.tust.app.domain.News;
import com.tust.app.domain.Users;
import com.tust.app.services.UsersService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsersServiceImpl implements UsersService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Users> getAllUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    public Boolean delUser(Integer id) {
        return userMapper.deleteUsersById(id);
    }

    @Override
    public List<Users> search(String username) {
        return userMapper.selectNewsByUsername(username);
    }

    @Override
    public Boolean modifyUsers(Users users) {
        return userMapper.updateUsers(users);
    }

    @Override
    public Users searchUserById(Integer id) {
        return userMapper.selectUsersById(id);
    }

    @Override
    public Integer selectIdByUsername(String username) {
        return userMapper.selectIdByUsername(username);
    }

    @Override
    public Integer registerUser(Users user) {
        if (isUsernameExists(user.getUsername())) {
            return 0; // 用户名已存在，注册失败
        }
        return userMapper.createUser(user);
    }

    @Override
    public boolean isUsernameExists(String username) {
        Integer id = userMapper.selectIdByUsername(username);
        Users user = userMapper.selectUsersById(id);
        return user != null;
    }


}
