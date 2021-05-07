package com.miscorf.controller;


import com.alibaba.fastjson.JSONObject;
import com.miscorf.pojo.ListQuery;
import com.miscorf.pojo.ResponseJson;
import com.miscorf.pojo.Template;
import com.miscorf.pojo.User;
import com.miscorf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @RequestMapping(value = "/all")
    @ResponseBody
    public ResponseJson notice_all(@RequestBody ListQuery listQuery) {
        System.out.println(listQuery);
        ResponseJson responseJson = new ResponseJson();
        int begin_num = (listQuery.getPage()-1)*listQuery.getLimit();
        String user_name='%'+listQuery.getTitle()+'%';
        String user_right='%'+listQuery.getType()+'%';
        System.out.println(user_name+user_right);
        List<User> users= userService.queryAllUserPage(begin_num,listQuery.getLimit(),user_name,user_right);
        Map<String, Object> map = new HashMap();
        map.put("items", users);
        map.put("total",userService.queryAllUser().size());
        responseJson.setData(map);
        return responseJson;
    }
    @RequestMapping(value = "/allUser")
    @ResponseBody
    public ResponseJson allUser() {

        ResponseJson responseJson = new ResponseJson();

        responseJson.setData(userService.queryAllUser());
        return responseJson;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public ResponseJson user_update(@RequestBody User user) {
        ResponseJson responseJson = new ResponseJson();
        System.out.println(user);
        if (userService.updateUser(user)){
            return responseJson;
        }
        else {
            responseJson.setCode(50000);
            return responseJson;
        }
    }
    @RequestMapping(value = "/create")
    @ResponseBody
    public ResponseJson user_create(@RequestBody User user) {
        ResponseJson responseJson = new ResponseJson();
        System.out.println(user);
        if (userService.addUser(user)){
            return responseJson;
        }
        else {
            responseJson.setCode(50000);
            return responseJson;
        }
    }
    @RequestMapping(value = "/fetchByName")
    @ResponseBody
    public ResponseJson fetchByName(String user_name) {
        ResponseJson responseJson = new ResponseJson();
        System.out.println(user_name);
        User user = userService.queryUserByName(user_name);
        if (user!= null){
            responseJson.setData(user);
            return responseJson;
        }
        else {
            responseJson.setCode(50000);
            return responseJson;
        }
    }
    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public ResponseJson updatePassword(@RequestBody String user) {
        ResponseJson responseJson = new ResponseJson();
        JSONObject jsonObject = JSONObject.parseObject(user);
        System.out.println(user);
        String name = jsonObject.getString("user_name");
        String password = jsonObject.getString("user_password");
        String first = jsonObject.getString("first");
        User user1 = userService.queryUserByName(name);
        if (user1.getUser_password().equals(password)){
            userService.updatePassword(name,first);
        }
        else{
            responseJson.setStatus("密码错误");
        }
       return  responseJson;
    }


}
