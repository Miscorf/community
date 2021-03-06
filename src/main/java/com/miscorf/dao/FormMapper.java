package com.miscorf.dao;

import com.miscorf.pojo.Answer;
import com.miscorf.pojo.Form;
import com.miscorf.pojo.Template;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FormMapper {
    boolean addForm(Form form);
    boolean deleteForm(int id);
    Form getFormById(int id);
    boolean addTemplate(Template template);
    List<Template>  queryAllTemplate();
    Template queryTemplateById(int id);
    List<Form>  queryAllForm();
    Form getFormByForm(Form form);
    Form getFormByName(String form_name);
    List<Form> getFormList(@Param("name")String name,@Param("form_creator")String form_creator, @Param("begin_num") int begin_num, @Param("page_size") int page_size);
    List<Form> getFormListTotal(@Param("name")String name,@Param("form_creator")String form_creator);
    List<Form> getRecentlyFormStatus();
}
