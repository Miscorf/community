package com.miscorf.service.Impl;

import com.miscorf.dao.AnswerMapper;
import com.miscorf.dao.FormMapper;
import com.miscorf.dao.UserMapper;
import com.miscorf.pojo.Answer;
import com.miscorf.pojo.Form;
import com.miscorf.pojo.Template;
import com.miscorf.pojo.User;
import com.miscorf.service.FormService;

import java.util.List;
import java.util.Map;

public class FormServiceImpl implements FormService {
    FormMapper formMapper;
    AnswerMapper answerMapper;
    UserMapper userMapper;

    public void setAnswerMapper(AnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }
    public void setFormMapper(FormMapper formMapper) {
        this.formMapper = formMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper=userMapper;
    }
    public boolean addForm(Form form) {
        return this.formMapper.addForm(form);
    }

    public Form getFormById(int id) {
        return this.formMapper.getFormById(id);
    }

    public boolean addTemplate(Template template) {
        return formMapper.addTemplate(template);
    }

    public List<Template> queryAllTemplate() {
        return formMapper.queryAllTemplate();
    }

    public Template queryTemplateById(int id) {
        return formMapper.queryTemplateById(id);
    }

    public List<Form> queryAllForm() {
        return formMapper.queryAllForm();
    }

    public Answer getAnswerByFormIdUserName(int form_id, int user_name) {
        return answerMapper.getAnswerByFormIdUserName(form_id,user_name);
    }

    public boolean addAnswer(Answer answer) {
        return answerMapper.addAnswer(answer);
    }

    public boolean addAnswerByFromId(int form_id) {
        List<User> users=  userMapper.queryAllUser();

        try {
            for (User user:users) {
                answerMapper.addAnswerAll(form_id,user.getUser_name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public Form getFormByForm(Form form) {
        return formMapper.getFormByForm(form);
    }

    public Form getFormByName(String form_name) {
        return formMapper.getFormByName(form_name);
    }

    public List<Answer> getUserFormList(String user_name,int begin_num,int page_size) {
        return this.answerMapper.getUserFormList(user_name,begin_num,page_size);
    }

    public boolean updateAnswer(Answer answer) {
        return this.answerMapper.updateAnswer(answer);
    }

    public List<Answer> getAnswerListByFormId(int form_id, int begin_num, int page_size) {
        return answerMapper.getAnswerListByFormId(form_id,begin_num,page_size);
    }

    public List<Answer> getAllAnswerByFormId(int form_id) {
        return answerMapper.getAllAnswerByFormId(form_id);
    }

    public boolean deleteForm(int id) {
        try {
            formMapper.deleteForm(id);
            answerMapper.deleteAnswerByFormId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public List<Answer> searchAnswer(String user_name, int begin_num, int page_size) {
        return answerMapper.searchAnswer(user_name,begin_num,page_size);
    }

    public List<Answer> searchAllAnswerByName(String user_name) {
        return answerMapper.searchAllAnswerByName(user_name);
    }

}
