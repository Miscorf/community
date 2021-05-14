package com.miscorf.controller;


import com.alipay.api.AlipayApiException;
import com.miscorf.pojo.*;
import com.miscorf.service.PayService;
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
    @RequestMapping("/creatPay")
    @ResponseBody
    public ResponseJson create(@RequestBody Pay pay) {
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
        responseJson.setData(payService.getAllPay(listQuery.getPage()-1,listQuery.getLimit()));
        System.out.println(responseJson);

        return responseJson;
    }
    @RequestMapping("/allUserPay")
    @ResponseBody
    public ResponseJson allUserPay(@RequestBody ListQuery listQuery) {
        System.out.println(listQuery);
        ResponseJson responseJson = new ResponseJson();

        responseJson.setData(payService.getUserPayByName(listQuery.getPage()-1,listQuery.getLimit(),listQuery.getUser_name()));
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
    public ResponseJson getAllPayItems() {
        ResponseJson responseJson = new ResponseJson();
        try {
            List<PayItem> list = payService.getAllPayItem();
            responseJson.setData(list);
        }catch (Exception e){
            e.printStackTrace();
        }
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
        return  responseJson;
    }


}
