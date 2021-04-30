package com.miscorf.controller;
import com.miscorf.pojo.ResponseJson;
import com.miscorf.pojo.User;
import com.miscorf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


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

        if(data_user!=null&&user.getUser_password().equals(data_user.getUser_password())){
            //生成token
            String token = UUID.randomUUID()+"";
            userService.setTokenUserName(user.getUser_name(),token);
            map.put("token",token);
        }
        else {
            responseJson.setCode(50000);
        }
        responseJson.setData(map);
        System.out.println("helollo:"+responseJson);
        return responseJson;
    }


//    public ResponseJson login2(@RequestBody  User user) {
//        System.out.println(user);
//        ResponseJson responseJson = new ResponseJson();
//        Map<String, Object> map = new HashMap();
//        User data_user=userService.queryUserByName(user.getUser_name());
//        if(data_user!=null&&user.getUser_password().equals(data_user.getUser_password())){
//            responseJson.setCode(20000);
//            map.put("roles",data_user.getUser_right());
//            map.put("name",data_user.getUser_name());
//        }
//        else {
//            responseJson.setCode(50000);
//        }
//        responseJson.setData(map);
//        return responseJson;
//    }
    @RequestMapping("/info")
    @ResponseBody
    public ResponseJson login_info(String token) {
        ResponseJson responseJson = new ResponseJson();
        Map<String, Object> map = new HashMap();

        System.out.println(token);
        User user = userService.selectTokenUser(token);
        System.out.println(user);
        map.put("name",user.getUser_name());
        responseJson.setData(map);
        System.out.println(responseJson);
        return responseJson;
    }
//    @RequestMapping("/info")
//    @ResponseBody
//    public Map<String, Object> ajax3() {
//        Map<String, Object> result = new HashMap();
//        Map<String, Object> map = new HashMap();
//        map.put("roles","admin");
//        result.put("code", Integer.valueOf(20000));
//        result.put("status", "success");
//        result.put("data",map);
//        return result;
//    }
    @RequestMapping("/logout")
    @ResponseBody
    public ResponseJson login_out() {
        ResponseJson responseJson = new ResponseJson();
        responseJson.setData("success");
        return responseJson;
    }

}
