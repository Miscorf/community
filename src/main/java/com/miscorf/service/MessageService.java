package com.miscorf.service;

import com.miscorf.pojo.Message;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface MessageService {
    boolean addMessage(Message message);
    List<Message> allMessage();
    List<Message> selectMessageByName(String user_name);
    List<Message> selectMessageBeforeDate(Timestamp message_date);
}
