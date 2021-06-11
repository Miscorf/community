package com.miscorf.service;

import com.miscorf.dao.PayItemMapper;
import com.miscorf.pojo.Pay;
import com.miscorf.pojo.PayItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PayService {

    //payMapper
    boolean creatPay(Pay pay);
    boolean updatePay(Pay pay);
    boolean deletePay(int  pay_table_id);
    boolean creatPayUsers(Pay pay);
    List<Pay> getAllPay(String name,String creator, int begin_num,int page_size);
    List<Pay> getAllPayTotal(String name,String creator);

    List<Pay> getUserPayByName( int begin_num, int page_size,String  user_name,String name);
    List<Pay> getUserPayTotal(@Param("user_name")String user_name,@Param("name")String name);

    List<Pay> getPayByTableId(@Param("pay_table_id") int id,@Param("begin_num") int begin_num,@Param("page_size") int page_size);
    List<Pay> getAllPayByTableId(@Param("pay_table_id") int id);


    //payItemMapper
    boolean creatPayItem(PayItem payItem);
    boolean updatePayItem(PayItem payItem);
    List<PayItem> getAllPayItem();
    List<PayItem> getAllPayItemPage(@Param("begin_num") int begin_num, @Param("page_size") int page_size,String name);
    boolean deletePayItem(PayItem payItem);

    boolean creatPayTableItems(PayItem payItem);
    boolean deletePayTableItemsByTableId(int id);
    List<PayItem> getAllPayTableItems();
    List<PayItem> getAllPayTableItemsPage(@Param("begin_num") int begin_num, @Param("page_size") int page_size);
    List<PayItem> getPayTableItemsByTableId(int id);
    List<PayItem> getPayTableItemsByItemId(int id);
    Map<String,Object> getPayChart();
}
