package com.tust.app.services;

import com.tust.app.domain.News;
import com.tust.app.domain.Users;

import javax.xml.registry.infomodel.User;
import java.util.List;

public interface UsersService {

    List<Users> getAllUsers();

    Boolean delUser(Integer id);

    List<Users> search(String username);

    Boolean modifyUsers(Users users);

    Users searchUserById(Integer id);

    Integer selectIdByUsername(String username);

    Integer registerUser(Users user);

    boolean isUsernameExists(String username);

}
