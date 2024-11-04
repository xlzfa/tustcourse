package com.tust.app.dao;

import com.tust.app.domain.News;
import com.tust.app.domain.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface UserMapper {

    List<Users> selectAllUsers();
    Users selectUsersById(Integer id);
    List<Users> selectNewsByUsername(String username);

    Integer createUser(Users users);
    boolean deleteUsersById(Integer id);

    boolean updateUsers(Users users);

    Integer selectIdByUsername(String username);


}
