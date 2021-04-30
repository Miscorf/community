package com.miscorf.service;

import com.miscorf.pojo.Answer;
import com.miscorf.pojo.Form;
import com.miscorf.pojo.Template;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FormService {
    boolean addForm(Form form);
    Form getFormById(int id);
    boolean addTemplate(Template template);
    List<Template> queryAllTemplate();
    Template queryTemplateById(int id);
    List<Form>  queryAllForm();
    Answer getAnswerByFormIdUserName(int form_id, int user_name);
    boolean addAnswer(Answer answer);
    boolean addAnswerByFromId(int form_id);//在answer表中根据插入form_id所有用户的answer
    Form getFormByForm(Form form);
    Form getFormByName(String form_name);
    List<Answer> getUserFormList(String user_name,int begin_num,int page_size);
    boolean updateAnswer(Answer answer);

}
