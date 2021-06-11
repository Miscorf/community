package com.miscorf.controller;


import com.alibaba.fastjson.JSONObject;
import com.miscorf.dao.FormMapper;
import com.miscorf.pojo.*;
import com.miscorf.service.FormService;
import com.miscorf.service.PayService;
import com.miscorf.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.JdbcRowSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@CrossOrigin
@RequestMapping("/form")
public class FormController {
    @Autowired
    @Qualifier("FormServiceImpl")
    FormService formService;

    @Autowired
    @Qualifier("UserServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("PayServiceImpl")
    PayService payService;

    @RequestMapping("/createTemplate")
    @ResponseBody
    public ResponseJson create(@RequestBody String form) {
        ResponseJson responseJson = new ResponseJson();
        JSONObject jsonObject = JSONObject.parseObject(form);
        Template template =new Template();
        template.setName(jsonObject.getString("name"));
        template.setCreat_time(jsonObject.getTimestamp("creat_time"));
        template.setCreator(jsonObject.getString("creator"));
        template.setVisible(jsonObject.getBoolean("visible"));
        template.setData(jsonObject.getString("data"));
        try {
            formService.addTemplate(template);
        }catch (Exception e){
            System.out.println(template);
        }
        return responseJson;
    }
    @RequestMapping("/createForm")
    @ResponseBody
    public ResponseJson createForm(@RequestBody Form form ) {
        ResponseJson responseJson = new ResponseJson();
        System.out.println(form);
        form.setType(form.getType().toString());
        form.setForm_creat_time(new Timestamp(new Date().getTime()));
        if (form.getCreatType().equals("first")){
            form.setUsers(userService.queryAllUser());
        }
        if (formService.getFormByName(form.getForm_name())==null){
            System.out.println(form.getForm_creat_time());
            try {
                formService.addForm(form);
                System.out.println(formService.getFormByForm(form));
                formService.addAnswerByFromId(formService.getFormByForm(form).getForm_id(),form.getUsers());
            }catch (Exception e){
                e.printStackTrace();
            }

        }else {
            responseJson.setStatus("重名");
        }
        return responseJson;
    }
    @RequestMapping("/deleteForm")
    @ResponseBody
    public ResponseJson deleteForm(int id) {
        ResponseJson responseJson = new ResponseJson();
        System.out.println(id);
        try {
            formService.deleteForm(id);
        } catch (Exception e) {
            e.printStackTrace();
            responseJson.setCode(500);
        }
        return responseJson;
    }

    @RequestMapping("/allForm")
    @ResponseBody
    public ResponseJson allForm(@RequestBody ListQuery listQuery) {
        System.out.println(listQuery);
        ResponseJson responseJson = new ResponseJson();
        int begin_num = (listQuery.getPage()-1)*listQuery.getLimit();
        int size = listQuery.getLimit();
        String name = '%'+listQuery.getTitle() +'%';
        String creator = '%'+listQuery.getType() +'%';
        System.out.println(name+creator);
        List<Form> list = formService.getFormList(name,creator,begin_num, size);
        Map<String, Object> map = new HashMap();
        map.put("items", list);
        map.put("total",formService.getFormListTotal(name,creator).size());
       responseJson.setData(map);
        System.out.println(responseJson);
        return responseJson;
    }
    @RequestMapping("/allUserForm")
    @ResponseBody
    public ResponseJson allUserForm(@RequestBody ListQuery listQuery) {
        System.out.println(listQuery);
        ResponseJson responseJson = new ResponseJson();
        String title = '%'+listQuery.getTitle() +'%';
        String name = listQuery.getUser_name();
        int begin_num = (listQuery.getPage()-1)*listQuery.getLimit();
        int size = listQuery.getLimit();
        List<Answer> list = formService.getUserFormList(title,name,begin_num,size);
        Map<String, Object> map = new HashMap();
        map.put("items", list);
        map.put("total",formService.getUserFormTotal(title,name).size());
        responseJson.setData(map);
        System.out.println(responseJson);

        return responseJson;
    }
    @RequestMapping("/allTemplate")
    @ResponseBody
    public ResponseJson all() {
        ResponseJson responseJson = new ResponseJson();
        responseJson.setData(formService.queryAllTemplate());
        System.out.println(responseJson);
        return responseJson;
    }
        @RequestMapping("/getFormById")
    @ResponseBody
    public ResponseJson getFormById(int id) {
        //System.out.println(id);
        ResponseJson responseJson = new ResponseJson();
        Template template = formService.queryTemplateById(formService.getFormById(id).getTemplate_id());
        Form form = formService.getFormById(id);
        template.setForm_name(form.getForm_name());
        responseJson.setData(template);
        System.out.println(responseJson);
        return responseJson;
    }

    @RequestMapping("/saveAnswer")
    @ResponseBody
    public ResponseJson saveAnswer(@RequestBody String str) {
        System.out.println(str);
        ResponseJson responseJson = new ResponseJson();
        JSONObject jsonObject = JSONObject.parseObject(str);
        Answer answer = new Answer();
        answer.setUser_name(jsonObject.getString("user_name"));
        answer.setAnswer_time(jsonObject.getTimestamp("answer_time"));
        answer.setForm_id(jsonObject.getInteger("form_id"));
        answer.setAnswer_type(jsonObject.getBoolean("answer_type"));
        answer.setAnswer_content(jsonObject.getString("answer_content"));
        System.out.println(answer);
        formService.updateAnswer(answer);
        System.out.println(answer);
        return responseJson;
    }

    @RequestMapping("/getAnswerList")
    @ResponseBody
    public ResponseJson getAnswerList(@RequestBody ListQuery listQuery) {
        System.out.println(listQuery);
        ResponseJson responseJson = new ResponseJson();
        List<Answer> list = formService.getAnswerListByFormId(listQuery.getId(),(listQuery.getPage()-1)*listQuery.getLimit(),listQuery.getLimit());
        Map<String, Object> map = new HashMap();
        map.put("items", list);
        map.put("total",formService.getAllAnswerByFormId(listQuery.getId()).size());
        responseJson.setData(map);
        System.out.println(responseJson);
        return responseJson;
    }
    @RequestMapping(value = "/searchAnswer")
    @ResponseBody
    public ResponseJson searchAnswer(@RequestBody ListQuery listQuery) {
        ResponseJson responseJson = new ResponseJson();
        System.out.println(listQuery);
        int begin_num = (listQuery.getPage()-1)*listQuery.getLimit();
        int size = listQuery.getLimit();
        String content='%'+listQuery.getTitle()+'%';
        List<Answer> list = formService.searchAnswer(content,begin_num,size);
        Map<String, Object> map = new HashMap();
        map.put("items", list);
        map.put("total",formService.searchAllAnswerByName(content).size());
        responseJson.setData(map);
        return responseJson;
    }
    @RequestMapping(value = "/getChart")
    @ResponseBody
    public ResponseJson getFormChart() {
        ResponseJson responseJson = new ResponseJson();
        Map<String, Object> map = new HashMap();
        Map<String,Object>  formChart = formService.getFormChart();
        Map<String,Object>  payChart = payService.getPayChart();
        map.put("form",formChart);
        map.put("pay",payChart);
        responseJson.setData(map);
        return responseJson;
    }

}
