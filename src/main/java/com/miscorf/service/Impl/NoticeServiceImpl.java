package com.miscorf.service.Impl;

import com.miscorf.dao.NoticeMapper;
import com.miscorf.pojo.Notice;
import com.miscorf.service.NoticeService;

import java.util.List;

public class NoticeServiceImpl implements NoticeService {
    NoticeMapper noticeMapper;
    public void setNoticeMapper(NoticeMapper noticeMapper) {
        this.noticeMapper= noticeMapper;
    }
    public boolean addNotice(Notice notice) {
        return this.noticeMapper.addNotice(notice);
    }

    public boolean deleteNoticeById(int notice_id) {
        return this.noticeMapper.deleteNoticeById(notice_id);
    }

    public boolean updateNotice(Notice notice) {
        return this.noticeMapper.updateNotice(notice);
    }

    public List<Notice> queryAllNotice() {
        return this.noticeMapper.queryAllNotice();
    }

    public List<Notice> queryNoticeByRight(String notice_right) {
        return this.noticeMapper.queryNoticeByRight(notice_right);
    }

    public Notice queryNoticeById(int notice_id) {
        return this.noticeMapper.queryNoticeById(notice_id);
    }


}
