package com.miscorf.dao;

import com.miscorf.pojo.Message;

import java.sql.Timestamp;
import java.util.List;

public interface MessageMapper {
    boolean addMessage(Message message);
    List<Message> allMessage();
    List<Message> selectMessageByName(String user_name);
    List<Message> selectMessageBeforeDate(Timestamp message_date);
}
