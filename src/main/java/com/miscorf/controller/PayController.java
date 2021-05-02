package com.miscorf.controller;


import com.alibaba.fastjson.JSONObject;
import com.miscorf.pojo.*;
import com.miscorf.service.FormService;
import com.miscorf.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        System.out.println();
        try {payService.creatPayUsers(pay);
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
}
