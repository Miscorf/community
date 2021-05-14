package com.miscorf.service.Impl;

import com.miscorf.dao.PayItemMapper;
import com.miscorf.dao.PayMapper;
import com.miscorf.pojo.Pay;
import com.miscorf.pojo.PayItem;
import com.miscorf.pojo.User;
import com.miscorf.service.PayService;

import java.util.List;

public class PayServiceImpl implements PayService {
    private PayMapper payMapper;
    private PayItemMapper payItemMapper;
    public void setPayMapper(PayMapper payMapper) {
        this.payMapper=payMapper;
    }

    public void setPayItem(PayItemMapper payItemMapper) {
        this.payItemMapper =payItemMapper;
    }
    public boolean creatPay(Pay pay) {
        return payMapper.creatPay(pay);
    }

    public boolean creatPayUsers(Pay pay) {
        payMapper.creatPay(pay);
        int pay_table_id = pay.getPay_table_id();
        System.out.println("pay_table_id="+pay_table_id);
        try {
            for (User user : pay.getPay_user()) {
                Pay pay_data = new Pay();
                pay_data.setUser_name(user.getUser_name());
                pay_data.setPay_table_id(pay_table_id);
                payMapper.creatPayUsers(pay_data);
            }
            for (PayItem payItem : pay.getPayItems()) {
                payItem.setPay_table_id(pay_table_id);
               payItemMapper.creatPayTableItems(payItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Pay> getAllPay(int begin_num, int page_size) {
        try {
            List<Pay> list = payMapper.getAllPay(begin_num,page_size) ;
            for (Pay pay:list
                 ) {
                pay.setPayItems(getPayTableItemsByTableId(pay.getPay_table_id()));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pay> getUserPayByName(int begin_num, int page_size, String user_name) {

        try {

            List<Pay> list = payMapper.getUserPayByName(begin_num,page_size,user_name);
            System.out.println("list!!!="+list);
            for (Pay pay:list
            ) {
                pay.setPayItems(getPayTableItemsByTableId(pay.getPay_table_id()));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pay> getPayByTableId(int id, int begin_num, int page_size) {
        return payMapper.getPayByTableId(id,begin_num,page_size);
    }

    public List<Pay> getAllPayByTableId(int id) {
        return payMapper.getAllPayByTableId(id);
    }

    public boolean creatPayItem(PayItem payItem) {
        return payItemMapper.creatPayItem(payItem);
    }

    public boolean updatePayItem(PayItem payItem) {
        return payItemMapper.updatePayItem(payItem);
    }

    public List<PayItem> getAllPayItem() {
        return payItemMapper.getAllPayItem();
    }

    public List<PayItem> getAllPayItemPage(int begin_num, int page_size) {
        return payItemMapper.getAllPayItemPage(begin_num,page_size);
    }

    public boolean deletePayItem(PayItem payItem) {
        return payItemMapper.deletePayItem(payItem);
    }

    public boolean creatPayTableItems(PayItem payItem) {
        return payItemMapper.creatPayTableItems(payItem);
    }

    public boolean deletePayTableItemsByTableId(int id) {
        return payItemMapper.deletePayTableItemsByTableId(id);
    }

    public List<PayItem> getAllPayTableItems() {
        return payItemMapper.getAllPayTableItems();
    }

    public List<PayItem> getAllPayTableItemsPage(int begin_num, int page_size) {
        return payItemMapper.getAllPayTableItemsPage(begin_num,page_size);
    }

    public List<PayItem> getPayTableItemsByTableId(int id) {
        return payItemMapper.getPayTableItemsByTableId(id);
    }

    public List<PayItem> getPayTableItemsByItemId(int id) {
        return payItemMapper.getPayTableItemsByItemId(id);
    }

}
