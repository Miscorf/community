package com.miscorf.service;

import com.miscorf.pojo.House;
import com.miscorf.pojo.User;
import org.apache.ibatis.annotations.Param;
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
    boolean updatePassword(String user_name , String user_password);
    boolean updateUserImage(User user);
    List<User> queryAdmin();
    boolean addHouse(House house);
    List<House> allHouse();
    boolean deleteHouse(House house );
    List<House> querryHouseByName(@Param("begin_num") int begin_num, @Param("page_size") int page_size,@Param("house_user_name") String house_user_name);
    boolean updateHouse(House house);
    List<House> allHousePage(@Param("begin_num") int begin_num, @Param("page_size") int page_size, @Param("house_user_name") String house_user_name);
    List<House> allUserHouse(@Param("user_name")String user_name);
}
