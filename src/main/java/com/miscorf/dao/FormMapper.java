package com.miscorf.dao;

import com.miscorf.pojo.Answer;
import com.miscorf.pojo.Form;
import com.miscorf.pojo.Template;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FormMapper {
    boolean addForm(Form form);
    Form getFormById(int id);
    boolean addTemplate(Template template);
    List<Template>  queryAllTemplate();
    Template queryTemplateById(int id);
    List<Form>  queryAllForm();
    Form getFormByForm(Form form);
    Form getFormByName(String form_name);
}
