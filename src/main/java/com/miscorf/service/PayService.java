package com.miscorf.service;

import com.miscorf.dao.PayItemMapper;
import com.miscorf.pojo.Pay;
import com.miscorf.pojo.PayItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PayService {

    //payMapper
    boolean creatPay(Pay pay);
    boolean creatPayUsers(Pay pay);
    List<Pay> getAllPay(int begin_num,int page_size);
    List<Pay> getUserPayByName( int begin_num, int page_size,String  user_name);
    List<Pay> getPayByTableId(@Param("pay_table_id") int id,@Param("begin_num") int begin_num,@Param("page_size") int page_size);
    List<Pay> getAllPayByTableId(@Param("pay_table_id") int id);


    //payItemMapper
    boolean creatPayItem(PayItem payItem);
    boolean updatePayItem(PayItem payItem);
    List<PayItem> getAllPayItem();
    List<PayItem> getAllPayItemPage(@Param("begin_num") int begin_num, @Param("page_size") int page_size);
    boolean deletePayItem(PayItem payItem);

    boolean creatPayTableItems(PayItem payItem);
    boolean deletePayTableItemsByTableId(int id);
    List<PayItem> getAllPayTableItems();
    List<PayItem> getAllPayTableItemsPage(@Param("begin_num") int begin_num, @Param("page_size") int page_size);
    List<PayItem> getPayTableItemsByTableId(int id);
    List<PayItem> getPayTableItemsByItemId(int id);
}
