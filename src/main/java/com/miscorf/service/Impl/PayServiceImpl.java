package com.miscorf.service.Impl;

import com.miscorf.dao.PayItemMapper;
import com.miscorf.dao.PayMapper;
import com.miscorf.pojo.*;
import com.miscorf.service.PayService;
import org.apache.ibatis.binding.MapperMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public boolean updatePay(Pay pay) {
        return payMapper.updatePay(pay);
    }

    @Override
    public boolean deletePay(int pay_table_id) {
        try {
            payMapper.deletePay(pay_table_id);
            payItemMapper.deletePayTableItemsByTableId(pay_table_id);
            payMapper.deletePayTable(pay_table_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
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

    public List<Pay> getAllPay(String name,String creator,int begin_num, int page_size) {
        try {
            List<Pay> list = payMapper.getAllPay(name,creator,begin_num,page_size) ;
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

    @Override
    public List<Pay> getAllPayTotal(String name, String creator) {
        return payMapper.getAllPayTotal(name,creator);
    }

    public List<Pay> getUserPayByName(int begin_num, int page_size, String user_name,String name) {

        try {

            List<Pay> list = payMapper.getUserPayByName(begin_num,page_size,user_name,name);
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

    @Override
    public List<Pay> getUserPayTotal(String user_name, String name) {
        return payMapper.getUserPayTotal(user_name,name);
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

    public List<PayItem> getAllPayItemPage(int begin_num, int page_size,String name) {
        return payItemMapper.getAllPayItemPage(begin_num,page_size,name);
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
    @Override
    public Map<String,Object> getPayChart() {
        List<Pay> payList =payMapper.getRecentlyPayStatus();
        System.out.println("!!!   System.out.printlnpays();");
        System.out.println(payList);
        List<FormChart> formCharts = new ArrayList<>() ;
        for (Pay f:payList
        ) {
            FormChart formChart = new FormChart();
            formChart.setAnwserCount(payMapper.getPayedCount(f.getPay_table_id()));
            formChart.setTitle(f.getPay_title());
            formChart.setTotal(payMapper.getAllPayByTableId(f.getPay_table_id()).size());
            System.out.println(formChart);
            formCharts.add(formChart);
        }
        System.out.println("!!!   System.out.println(formList);");
        List<String> titles = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        List<Integer> answer_count = new ArrayList<>();

        for (FormChart f :formCharts
        ) {
            titles.add(f.getTitle());
            count.add(f.getTotal()-f.getAnwserCount());
            answer_count.add(f.getAnwserCount());
        }
        Map<String,Object> map = new MapperMethod.ParamMap<>();
        map.put("titles",titles);
        map.put("count",count);
        map.put("answer_count",answer_count);
        return map;
    }
}
