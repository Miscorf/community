package com.miscorf.service.Impl;

import com.miscorf.dao.HouseMapper;
import com.miscorf.dao.UserMapper;
import com.miscorf.pojo.House;
import com.miscorf.pojo.User;
import com.miscorf.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    private HouseMapper houseMapper;
    public void setUserMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    public void setHouseMapper(HouseMapper houseMapper) { this.houseMapper=houseMapper; }
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

    public boolean updatePassword(String user_name, String user_password) {
        return userMapper.updatePassword(user_name,user_password);
    }

    public boolean updateUserImage(User user) {
        return userMapper.updateUserImage(user);
    }

    @Override
    public List<User> queryAdmin() {
        return userMapper.queryAdmin();
    }

    @Override
    public boolean addHouse(House house) {
        return houseMapper.addHouse(house);
    }

    @Override
    public List<House> allHouse() {
        return houseMapper.allHouse();
    }

    @Override
    public boolean deleteHouse(House house) {
        return houseMapper.deleteHouse(house);
    }

    @Override
    public List<House> querryHouseByName(int begin_num, int page_size, String house_user_name) {
        return houseMapper.querryHouseByName(begin_num,page_size,house_user_name);
    }


    @Override
    public boolean updateHouse(House house) {
        return houseMapper.updateHouse(house);
    }

    @Override
    public List<House> allHousePage(int begin_num, int page_size, String house_user_name) {
        List<House> list = houseMapper.allHousePage(begin_num,page_size,house_user_name);
        for (House h:list
             ) {
            h.setHouse_host(userMapper.queryUserByName(h.getHouse_user_name()).getUser_id_name());
            h.setUser_phone(userMapper.queryUserByName(h.getHouse_user_name()).getUser_phone());
        }
        return list;
    }

    @Override
    public List<House> allUserHouse(String user_name) {
        return houseMapper.allUserHouse(user_name);
    }

}
