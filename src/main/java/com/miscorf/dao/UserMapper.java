package com.miscorf.dao;

import com.miscorf.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized;

import java.util.List;

public interface UserMapper {
    //增加一个User
    boolean addUser(User user);
    //根据id删除一个Book
    boolean deleteUserByID(int user_id);
    //更新user
    boolean updateUser(User user);
    //根据id查询,返回一个
    User queryUserById(int user_id);
    //查询全部user,返回list集合
    List<User> queryAllUser();

    User queryUserRight(int  user_id);

    List<User> queryAllUserPage(@Param("begin_num") int begin_num,@Param("page_size") int page_size,@Param("user_name") String user_name,@Param("user_right") String user_right);
    User queryUserByName(String user_name);
    boolean setTokenUser(@Param("user_id")int  user_id , @Param("token")String token);
    boolean setTokenUserName(@Param("user_name")String  user_name , @Param("token")String token);
    User selectTokenUser(@Param("token")String token);
    boolean updatePassword(@Param("user_name")String user_name , @Param("user_password")String user_password);
    boolean updateUserImage(User user);
    List<User> queryAdmin();

}
