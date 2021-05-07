package com.miscorf.controller;

import com.miscorf.pojo.Notice;
import com.miscorf.pojo.ResponseJson;
import com.miscorf.service.NoticeService;
import com.miscorf.util.QiniuCloudUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


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
        System.out.println(id);
        Notice notice= noticeService.queryNoticeById(id);
        System.out.println(notice);
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
    @RequestMapping(value = "/creat")
    @ResponseBody
    public ResponseJson creat(@RequestBody Notice notice) {

        ResponseJson responseJson = new ResponseJson();
        System.out.println(notice);
        if (notice.getNotice_id()!=0){
            noticeService.updateNotice(notice);
            return responseJson;
        }
        else {
            noticeService.addNotice(notice);
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
    @ResponseBody
    @RequestMapping(value="/uploadImg", method=RequestMethod.POST)
    public ResponseJson uploadImg(@RequestParam("uploadFile") MultipartFile image, HttpServletRequest request) {
        ResponseJson result = new ResponseJson();
        System.out.println(image);
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
                result.setStatus("文件上传成功");
                result.setData(url);
                System.out.println(url);
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
