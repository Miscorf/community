package com.miscorf.dao;

import com.miscorf.pojo.Pay;
import com.miscorf.pojo.User;
import org.apache.ibatis.annotations.Param;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface PayMapper {
    boolean creatPay(Pay pay);
    int  creatPayUsers(Pay pay);
    Pay selectPay(Pay pay);
    List<Pay> getAllPay(@Param("begin_num") int begin_num,@Param("page_size") int page_size);
    List<Pay> getUserPayByName(@Param("begin_num") int begin_num,@Param("page_size") int page_size,@Param("user_name")String user_name);
}
