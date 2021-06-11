package com.miscorf.service;

import com.miscorf.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    boolean addAnswerByFromId(int form_id,List<User> users);//在answer表中根据插入form_id所有用户的answer
    Form getFormByForm(Form form);
    Form getFormByName(String form_name);
    List<Answer> getUserFormList(String title,String user_name,int begin_num,int page_size);
    boolean updateAnswer(Answer answer);
    List<Answer> getAnswerListByFormId(int form_id,int begin_num,int page_size);
    List<Answer> getAllAnswerByFormId( int form_id);
    boolean deleteForm(int id);
    List<Answer> searchAnswer(@Param("user_name") String user_name,@Param("begin_num") int begin_num, @Param("page_size") int page_size);
    List<Answer> searchAllAnswerByName(@Param("user_name") String user_name);
    List<Form> getFormList(@Param("name")String name,@Param("form_creator")String form_creator, @Param("begin_num") int begin_num, @Param("page_size") int page_size);
    List<Answer> getUserFormTotal(@Param("title")String title,@Param("user_name")String user_name);
    List<Form> getFormListTotal(@Param("name")String name,@Param("form_creator")String form_creator);
    Map<String,Object> getFormChart();

}
