package com.miscorf.service.Impl;

import com.miscorf.dao.MessageMapper;
import com.miscorf.pojo.Message;
import com.miscorf.service.MessageService;

import java.sql.Timestamp;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    MessageMapper messageMapper;
    public boolean addMessage(Message message) {
        return messageMapper.addMessage(message);
    }

    public List<Message> allMessage() {
        return messageMapper.allMessage();
    }

    public List<Message> selectMessageByName(String user_name) {
        return messageMapper.selectMessageByName(user_name);
    }

    public List<Message> selectMessageBeforeDate(Timestamp message_date) {
        return selectMessageBeforeDate(message_date);
    }

    public void setMessageMapper(MessageMapper messageMapper) {
        this.messageMapper=messageMapper;
    }
}
