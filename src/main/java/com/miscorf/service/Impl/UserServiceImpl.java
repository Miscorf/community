package com.miscorf.service.Impl;

import com.miscorf.dao.UserMapper;
import com.miscorf.pojo.User;
import com.miscorf.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }
    public boolean addUser(User user) {
        return userMapper.addUser(user);
    }

    public boolean deleteUserByID(int user_id) {
        return userMapper.deleteUserByID(user_id);
    }

    public boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public User queryUserById(int user_id) {
        return userMapper.queryUserById(user_id);
    }

    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    public User queryUserRight(int user_id) {
        return userMapper.queryUserRight(user_id);
    }

    public List<User> queryAllUserPage(int begin_num, int page_size, String user_name, String user_right) {
        return userMapper.queryAllUserPage(begin_num,page_size,user_name,user_right);
    }

    public User queryUserByName(String user_name) {
        return userMapper.queryUserByName(user_name);
    }

    public boolean setTokenUser(int user_id, String token) {
        return this.userMapper.setTokenUser(user_id,token);
    }

    public User selectTokenUser(String token) {
        return this.userMapper.selectTokenUser(token);
    }

    public boolean setTokenUserName(String user_name, String token) {
        return userMapper.setTokenUserName(user_name,token);
    }
}
