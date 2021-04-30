package com.miscorf.controller;


import com.miscorf.pojo.ListQuery;
import com.miscorf.pojo.ResponseJson;
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




   @RequestMapping(value={"/sub_login"}, consumes={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> user_Login(@RequestBody  User user, HttpServletRequest request, ModelMap modelMap){
        Map<String, Object> result = new HashMap();
        User data_user=userService.queryUserByName(user.getUser_name());
        if(data_user!=null&&user.getUser_password().equals(data_user.getUser_password())){
            result.put("code", Integer.valueOf(12));
        }
        else {
            result.put("code", Integer.valueOf(-12));
        }
        return result;

    }



}
