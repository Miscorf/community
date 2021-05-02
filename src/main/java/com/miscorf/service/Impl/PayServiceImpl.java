package com.miscorf.service.Impl;

import com.miscorf.dao.NoticeMapper;
import com.miscorf.dao.PayMapper;
import com.miscorf.pojo.Pay;
import com.miscorf.pojo.User;
import com.miscorf.service.PayService;
import org.springframework.web.bind.annotation.PatchMapping;
import sun.security.krb5.internal.PAData;

import java.util.List;

public class PayServiceImpl implements PayService {
    private PayMapper payMapper;
    public void setPayMapper(PayMapper payMapper) {
        this.payMapper=payMapper;
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

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Pay> getAllPay(int begin_num, int page_size) {
        return payMapper.getAllPay(begin_num,page_size);
    }

    public List<Pay> getUserPayByName(int begin_num, int page_size, String user_name) {
        return payMapper.getUserPayByName(begin_num,page_size,user_name);
    }


}
