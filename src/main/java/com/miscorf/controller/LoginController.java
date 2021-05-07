package com.miscorf.controller;
import com.miscorf.pojo.ResponseJson;
import com.miscorf.pojo.User;
import com.miscorf.service.UserService;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@CrossOrigin
@RequestMapping("/user")
public class LoginController {
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public ResponseJson login(@RequestBody  User user) {
        ResponseJson responseJson = new ResponseJson();
        Map<String, Object> map = new HashMap();
        User data_user=userService.queryUserByName(user.getUser_name());
        String token = UUID.randomUUID()+"";
        if(data_user!=null&&user.getUser_password().equals(data_user.getUser_password())){
            userService.setTokenUserName(user.getUser_name(),token);
            map.put("token",token);
        }
        else {
            responseJson.setCode(50000);
        }
        responseJson.setData(map);
        responseJson.setToken(token);
        System.out.println("helollo:"+responseJson);
        return responseJson;
    }

    @RequestMapping("/info")
    @ResponseBody
    public ResponseJson login_info(String token) {
        ResponseJson responseJson = new ResponseJson();
        Map<String, Object> map = new HashMap();

        System.out.println(token);
        User user = userService.selectTokenUser(token);
        System.out.println(user);
        map.put("name",user.getUser_name());
        List<String> list = new ArrayList<String>();
        list.add(user.getUser_right());
        map.put("roles",list);
        responseJson.setData(map);
        responseJson.setToken(token);

        System.out.println(responseJson);
        return responseJson;
    }
    @RequestMapping("/logout")
    @ResponseBody
    public ResponseJson login_out() {
        ResponseJson responseJson = new ResponseJson();
        responseJson.setData("success");
        return responseJson;
    }

}
