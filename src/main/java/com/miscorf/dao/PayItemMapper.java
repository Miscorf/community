package com.miscorf.dao;

import com.miscorf.pojo.PayItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayItemMapper {
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
