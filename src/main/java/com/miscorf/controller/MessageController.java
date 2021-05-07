package com.miscorf.controller;

import com.miscorf.pojo.Message;
import com.miscorf.pojo.ResponseJson;
import com.miscorf.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/message")
public class MessageController {
    @Autowired
    @Qualifier("MessageServiceImpl")
    MessageService messageService;
    @RequestMapping(value = "/all")
    @ResponseBody
    public ResponseJson all() {
        ResponseJson responseJson = new ResponseJson();
        List<Message> list = messageService.allMessage();
        responseJson.setData(list);
        return responseJson;
    }
    @RequestMapping(value = "/selectMessageByName")
    @ResponseBody
    public ResponseJson selectMessageByName(String user_name) {
        ResponseJson responseJson = new ResponseJson();
        List<Message> list = messageService.selectMessageByName(user_name);
        responseJson.setData(list);
        return responseJson;
    }
    @RequestMapping(value = "/selectMessageBeforeDate")
    @ResponseBody
    public ResponseJson selectMessageBeforeDate(Timestamp date) {
        ResponseJson responseJson = new ResponseJson();
        List<Message> list = messageService.selectMessageBeforeDate(date);
        responseJson.setData(list);
        return responseJson;
    }
    @RequestMapping(value = "/addMessage")
    @ResponseBody
    public ResponseJson selectMessageByName(@RequestBody Message message) {
        ResponseJson responseJson = new ResponseJson();
        if ( !messageService.addMessage(message)){
           responseJson.setCode(50000);
        }
        return responseJson;
    }
}
