package com.miscorf.controller;


import com.alipay.api.AlipayApiException;
import com.miscorf.pojo.*;
import com.miscorf.service.PayService;
import com.miscorf.service.UserService;
import com.miscorf.util.AlipayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/pay")
public class PayController {
    @Autowired
    @Qualifier("PayServiceImpl")
    PayService payService;

    @Autowired
    @Qualifier("UserServiceImpl")
    UserService userService;

    @RequestMapping("/creatPay")
    @ResponseBody
    public ResponseJson create(@RequestBody Pay pay) {
        if (pay.getCreatType().equals("first")){
            pay.setPay_user(userService.queryAllUser());
        }
        ResponseJson responseJson = new ResponseJson();
        System.out.println(pay);
        try {
            payService.creatPayUsers(pay);

        }catch (Exception e){
            e.printStackTrace();
        }
        return responseJson;
    }
    @RequestMapping("/allPay")
    @ResponseBody
    public ResponseJson allPay(@RequestBody ListQuery listQuery) {

        ResponseJson responseJson = new ResponseJson();
        String name = '%'+listQuery.getTitle() +'%';
        String creator = '%'+listQuery.getType() +'%';
        int begin_num = (listQuery.getPage()-1)*listQuery.getLimit();
        int size = listQuery.getLimit();
        Map<String, Object> map = new HashMap();
        System.out.println(name+creator+begin_num);
        List<Pay> list = payService.getAllPay(name,creator,begin_num,size);
        map.put("items", list);
        map.put("total",payService.getAllPayTotal(name,creator).size());
        responseJson.setData(map);
        System.out.println(responseJson);

        return responseJson;
    }
    @RequestMapping("/allUserPay")
    @ResponseBody
    public ResponseJson allUserPay(@RequestBody ListQuery listQuery) {
        System.out.println(listQuery);
        ResponseJson responseJson = new ResponseJson();
        String name = '%'+listQuery.getTitle() +'%';
        String user_name = listQuery.getUser_name();
        int begin_num = (listQuery.getPage()-1)*listQuery.getLimit();
        int size = listQuery.getLimit();
        Map<String, Object> map = new HashMap();
        List<Pay> list = payService.getUserPayByName(begin_num,size,user_name,name);
        map.put("items", list);
        map.put("total",payService.getUserPayTotal(user_name,name).size());
        responseJson.setData(map);
        System.out.println(responseJson);
        return responseJson;
    }
    @RequestMapping("/getPayList")
    @ResponseBody
    public ResponseJson getPayList(@RequestBody ListQuery listQuery) {
        System.out.println(listQuery);
        ResponseJson responseJson = new ResponseJson();
        List<Pay> list = payService.getPayByTableId(listQuery.getId(),listQuery.getPage()-1,listQuery.getLimit());
        Map<String, Object> map = new HashMap();
        map.put("items", list);
        map.put("total",payService.getAllPayByTableId(listQuery.getId()).size());
        responseJson.setData(map);
        System.out.println(responseJson);
        return responseJson;
    }
    @RequestMapping("/createPayItem")
    @ResponseBody
    public ResponseJson createPayItem(@RequestBody PayItem payItem) {
        ResponseJson responseJson = new ResponseJson();
        System.out.println(payItem);
        try {payService.creatPayItem(payItem);
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseJson;
    }
    @RequestMapping("/getAllPayItems")
    @ResponseBody
    public ResponseJson getAllPayItems(@RequestBody ListQuery listQuery) {
        ResponseJson responseJson = new ResponseJson();
        String name = '%'+listQuery.getTitle() +'%';
        String creator = '%'+listQuery.getType() +'%';
        System.out.println(listQuery);
        int begin_num = (listQuery.getPage()-1)*listQuery.getLimit();
        int size = listQuery.getLimit();
        System.out.println(name);

            List<PayItem> list = payService.getAllPayItemPage(begin_num,size,name);
            Map<String, Object> map = new HashMap();
            System.out.println(list);
            map.put("items", list);
            map.put("total",payService.getAllPayItem().size());
            responseJson.setData(map);
        return responseJson;
    }
    @RequestMapping(value = "/aliPay")
    @ResponseBody
    public ResponseJson aliPay(@RequestBody Pay pay) throws AlipayApiException {
        ResponseJson responseJson = new ResponseJson();
        System.out.println(pay);
         AlipayUtil alipayUtil = new AlipayUtil();
         alipayUtil.payAliPay(pay);
         responseJson.setData(alipayUtil.payAliPay(pay));
         pay.set_pay(true);
         payService.updatePay(pay);
        System.out.println(pay);
        return  responseJson;
    }
    @RequestMapping(value = "/updatePayItem")
    @ResponseBody
    public ResponseJson updatePayItem(@RequestBody PayItem pay)  {
        ResponseJson responseJson = new ResponseJson();
        System.out.println(pay);
        responseJson.setData(payService.updatePayItem(pay));
        return  responseJson;
    }

    @RequestMapping("/deletePay")
    @ResponseBody
    public ResponseJson deletePay(@RequestBody Pay pay) {
        ResponseJson responseJson = new ResponseJson();
        System.out.println(pay);
        try {
            payService.deletePay(pay.getPay_table_id());
        } catch (Exception e) {
            e.printStackTrace();
            responseJson.setCode(500);
        }
        return responseJson;
    }
    @RequestMapping("/checkPay")
    @ResponseBody
    public ResponseJson checkPay() {
        ResponseJson responseJson = new ResponseJson();
        System.out.println("adasd");

        return responseJson;
    }

}
