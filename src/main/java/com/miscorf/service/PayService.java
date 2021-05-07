package com.miscorf.service;

import com.miscorf.pojo.Pay;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PayService {
    boolean creatPay(Pay pay);
    boolean creatPayUsers(Pay pay);
    List<Pay> getAllPay(int begin_num,int page_size);
    List<Pay> getUserPayByName( int begin_num, int page_size,String  user_name);
    List<Pay> getPayByTableId(@Param("pay_table_id") int id,@Param("begin_num") int begin_num,@Param("page_size") int page_size);
    List<Pay> getAllPayByTableId(@Param("pay_table_id") int id);

}
