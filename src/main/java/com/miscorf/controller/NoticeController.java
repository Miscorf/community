package com.miscorf.controller;

import com.miscorf.pojo.Notice;
import com.miscorf.pojo.ResponseJson;
import com.miscorf.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@CrossOrigin
@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    @Qualifier("NoticeServiceImpl")

    private NoticeService noticeService;

    @RequestMapping(value = "/all")
    @ResponseBody
    public ResponseJson notice_all() {
        ResponseJson responseJson = new ResponseJson();
        List<Notice> notices= noticeService.queryAllNotice();
        responseJson.setData(notices);
        return responseJson;
    }
    @RequestMapping(value = "/fetch")
    @ResponseBody
    public ResponseJson notice_by_id(int id) {
        ResponseJson responseJson = new ResponseJson();
        Notice notice= noticeService.queryNoticeById(id);
        responseJson.setData(notice);
        return responseJson;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public ResponseJson notice_update(@RequestBody Notice notice) {
        ResponseJson responseJson = new ResponseJson();
        System.out.println(notice);
        if (noticeService.updateNotice(notice)){
            return responseJson;
        }
        else {
            responseJson.setCode(50000);
            return responseJson;
        }

    }
    @RequestMapping(value = "/delete")
    @ResponseBody
    public ResponseJson notice_delete(int id) {
        System.out.println(id);
        ResponseJson responseJson = new ResponseJson();

        if (noticeService.deleteNoticeById(id)){
            return responseJson;
        }
        else {
            responseJson.setCode(50000);
            return responseJson;
        }

    }
}
