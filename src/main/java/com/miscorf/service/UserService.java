package com.miscorf.service;

import com.miscorf.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    boolean addUser(User user);
    boolean deleteUserByID(int user_id);
    boolean updateUser(User  user);
    User queryUserById(int user_id);
    List<User> queryAllUser();
    User queryUserRight(int  user_id);
    List<User> queryAllUserPage(int begin_num,int page_size,String user_name,String user_right);
    User queryUserByName(String user_name);
    boolean setTokenUser(int user_id,String token);
    User selectTokenUser(String token);
    boolean setTokenUserName(String user_name,String token);
}
