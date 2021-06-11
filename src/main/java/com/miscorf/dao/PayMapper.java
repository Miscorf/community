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
    boolean updatePay(Pay pay);
    List<Pay> getAllPay(@Param("name")String name,@Param("creator")String creator, @Param("begin_num") int begin_num,@Param("page_size") int page_size);
    List<Pay> getAllPayTotal(@Param("name")String name,@Param("creator")String creator);
    List<Pay> getUserPayByName(@Param("begin_num") int begin_num,@Param("page_size") int page_size,@Param("user_name")String user_name,@Param("name")String name);
    List<Pay> getUserPayTotal(@Param("user_name")String user_name,@Param("name")String name);

    List<Pay> getPayByTableId(@Param("pay_table_id") int id,@Param("begin_num") int begin_num,@Param("page_size") int page_size);
    List<Pay> getAllPayByTableId(@Param("pay_table_id") int id);
    boolean deletePay(int id);
    boolean deletePayTable( int id);
    List<Pay> getRecentlyPayStatus();
    int getPayedCount(int id);
}
