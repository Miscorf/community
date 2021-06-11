package com.miscorf.controller;
import com.miscorf.pojo.ResponseJson;
import com.miscorf.pojo.User;
import com.miscorf.service.FormService;
import com.miscorf.service.NoticeService;
import com.miscorf.service.PayService;
import com.miscorf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@CrossOrigin
@RequestMapping("/user")
public class
LoginController {
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("FormServiceImpl")
    FormService formService;

    @Autowired
    @Qualifier("NoticeServiceImpl")
    private NoticeService noticeService;

    @Autowired
    @Qualifier("PayServiceImpl")
    PayService payService;

    @RequestMapping("/login")
    @ResponseBody
    public ResponseJson login(@RequestBody  User user) {
        ResponseJson responseJson = new ResponseJson();
        Map<String, Object> map = new HashMap();
        User data_user=userService.queryUserByName(user.getUser_name());
        String token = UUID.randomUUID()+"";
        if(data_user!=null&&user.getUser_password().equals(data_user.getUser_password())){
            if(userService.setTokenUserName(user.getUser_name(),token))
            map.put("token",token);
            else  responseJson.setCode(50000);
        }
        else {
            responseJson.setCode(50000);
        }
        responseJson.setData(map);
        responseJson.setToken(token);
        System.out.println("Login ResponseJson:"+responseJson);
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
        map.put("avatar",user.getUser_image());
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
    @RequestMapping("/getCount")
    @ResponseBody
    public ResponseJson getCount() {
        ResponseJson responseJson = new ResponseJson();
        Map<String, Object> map = new HashMap();

        map.put("payCount", payService.getAllPayTotal("%%", "%%").size());
        map.put("noticeCount", noticeService.queryAllNotice().size());
        map.put("userCount", userService.queryAllUser().size());
        map.put("formCount", formService.queryAllForm().size());
        System.out.println(map);
        responseJson.setData(map);
        return responseJson;
    }


}
