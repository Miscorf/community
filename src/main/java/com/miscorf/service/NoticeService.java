package com.miscorf.service;

import com.miscorf.pojo.Notice;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface NoticeService {
    boolean addNotice(Notice notice);
    boolean deleteNoticeById(int notice_id);
    boolean updateNotice(Notice notice);
    List<Notice> queryAllNotice();
    List<Notice> queryNoticeByRight(String notice_right);
    Notice queryNoticeById(int notice_id);
}
