package com.miscorf.controller;


import com.alibaba.fastjson.JSONObject;
import com.miscorf.pojo.ListQuery;
import com.miscorf.pojo.ResponseJson;
import com.miscorf.pojo.User;
import com.miscorf.service.UserService;
import com.miscorf.util.QiniuCloudUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


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
    @RequestMapping(value = "/updateImage")
    @ResponseBody
    public ResponseJson updateImage(@RequestBody User user) {
        ResponseJson responseJson = new ResponseJson();
        System.out.println(user);
        return  responseJson;
    }

    @ResponseBody
    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public ResponseJson upload(@RequestParam("uploadFile") MultipartFile image,
                               @RequestParam("user") String name,
                               @RequestParam("avatar") String avatar, HttpServletRequest request) {
        ResponseJson result = new ResponseJson();

        System.out.println(name);
        System.out.println(image);
        System.out.println(avatar);
        if (image.isEmpty()) {
            result.setCode(400);
            result.setStatus("文件为空，请重新上传");
            return result;
        }
        try {
            byte[] bytes = image.getBytes();
            String imageName = UUID.randomUUID().toString();
            QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
            try {
                //使用base64方式上传到七牛云
                String url = qiniuUtil.put64image(bytes, imageName);
                //String url= "http://img.miscorf.top/b449177c-f60d-4811-ab1f-146d4ad12006";
                User user = new User();
                user.setUser_name(name);
                user.setUser_image(url);
                userService.updateUserImage(user);
                //删除原头像
                qiniuUtil.delete(avatar);
                result.setStatus("文件上传成功");
                result.setData(user);


                System.out.println(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        } catch (IOException e) {
            result.setCode(500);
            result.setData("文件上传发生异常！");
            return result;
        }
    }



}
