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
    @RequestMapping(value = "/allMessage")
    @ResponseBody
    public ResponseJson allMessage() {
        ResponseJson responseJson = new ResponseJson();
        List<Message> list = messageService.allMessage();
        System.out.println(list);
        responseJson.setData(list);
        return responseJson;
    }
    @RequestMapping(value = "/allUserMessage")
    @ResponseBody
    public ResponseJson allUserMessage(String name) {
        ResponseJson responseJson = new ResponseJson();
        List<Message> list = messageService.selectMessageByName(name);
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
    @RequestMapping(value = "/createMessage")
    @ResponseBody
    public ResponseJson createMessage(@RequestBody Message message) {
        ResponseJson responseJson = new ResponseJson();
        if ( !messageService.addMessage(message)){
           responseJson.setCode(50000);
        }
        return responseJson;
    }
    @RequestMapping(value = "/updateMessage")
    @ResponseBody
    public ResponseJson updateMessage(@RequestBody Message message) {
        ResponseJson responseJson = new ResponseJson();
        if ( !messageService.updateMessage(message)){
            responseJson.setCode(50000);
        }
        return responseJson;
    }
    @RequestMapping(value = "/deleteMessage")
    @ResponseBody
    public ResponseJson deleteMessage(@RequestBody Message message) {
        ResponseJson responseJson = new ResponseJson();
        if ( !messageService.deleteMessage(message)){
            responseJson.setCode(50000);
        }
        return responseJson;
    }
    @RequestMapping(value = "/replyMessage")
    @ResponseBody
    public ResponseJson replyMessage(@RequestBody Message message) {
        ResponseJson responseJson = new ResponseJson();
        if ( !messageService.replyMessage(message)){
            responseJson.setCode(50000);
        }
        return responseJson;
    }


}
