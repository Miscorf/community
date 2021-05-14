package com.miscorf.dao;

import com.miscorf.pojo.Notice;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface NoticeMapper {
    boolean addNotice(Notice notice);
    boolean deleteNoticeById(int notice_id);
    boolean updateNotice(Notice notice);
    List<Notice> queryAllNotice();
    List<Notice> queryNoticeByRight(String notice_right);
    Notice queryNoticeById(int notice_id);
    List<Notice> searchNotice(@Param("content") String title, @Param("date") Timestamp date, @Param("begin_num") int begin_num, @Param("page_size") int page_size);
    List<Notice> fetchNotice(@Param("begin_num") int begin_num, @Param("page_size") int page_size);
}
