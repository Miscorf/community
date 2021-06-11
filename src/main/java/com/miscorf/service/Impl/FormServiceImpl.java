package com.miscorf.service.Impl;

import com.miscorf.dao.AnswerMapper;
import com.miscorf.dao.FormMapper;
import com.miscorf.dao.PayMapper;
import com.miscorf.dao.UserMapper;
import com.miscorf.pojo.*;
import com.miscorf.service.FormService;
import org.apache.ibatis.binding.MapperMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FormServiceImpl implements FormService {
    private FormMapper formMapper;
    private AnswerMapper answerMapper;
    private UserMapper userMapper;

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

    public boolean addAnswerByFromId(int form_id,List<User> users) {
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

    public List<Answer> getUserFormList(String title,String user_name,int begin_num,int page_size) {
        return this.answerMapper.getUserFormList(title,user_name,begin_num,page_size);
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

    @Override
    public List<Form> getFormList(String name, String form_creator, int begin_num, int page_size) {
        return formMapper.getFormList(name,form_creator,begin_num, page_size);
    }

    @Override
    public List<Answer> getUserFormTotal(String title,String user_name) {
        return answerMapper.getUserFormTotal(title, user_name);
    }

    @Override
    public List<Form> getFormListTotal(String name, String form_creator) {
        return formMapper.getFormListTotal(name,form_creator);
    }

    @Override
    public Map<String,Object>  getFormChart() {
        List<Form> formList = formMapper.getRecentlyFormStatus();
        System.out.println("!!!   System.out.println(formList);");
        System.out.println(formList);
        List<FormChart> formCharts = new ArrayList<>() ;
        for (Form f:formList
             ) {
            FormChart formChart = new FormChart();
            formChart.setAnwserCount(answerMapper.getAnswerCount(f.getForm_id()));
            formChart.setTitle(f.getForm_name());
            formChart.setTotal(answerMapper.getAllAnswerByFormId(f.getForm_id()).size());
            System.out.println(formChart);
            formCharts.add(formChart);
        }
        System.out.println("!!!   System.out.println(formList);");
        List<String> titles = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        List<Integer> answer_count = new ArrayList<>();

        for (FormChart f :formCharts
                ) {
            titles.add(f.getTitle());
            count.add(f.getTotal()-f.getAnwserCount());
            answer_count.add(f.getAnwserCount());
        }
        Map<String,Object> map = new MapperMethod.ParamMap<>();
        map.put("titles",titles);
        map.put("count",count);
        map.put("answer_count",answer_count);
        return map;
    }

}
