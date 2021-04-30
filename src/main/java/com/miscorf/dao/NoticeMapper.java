package com.miscorf.dao;

import com.miscorf.pojo.Notice;

import java.util.List;

public interface NoticeMapper {
    boolean addNotice(Notice notice);
    boolean deleteNoticeById(int notice_id);
    boolean updateNotice(Notice notice);
    List<Notice> queryAllNotice();
    List<Notice> queryNoticeByRight(String notice_right);
    Notice queryNoticeById(int notice_id);
}
