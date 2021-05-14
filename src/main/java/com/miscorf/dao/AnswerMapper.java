package com.miscorf.dao;

import com.miscorf.pojo.Answer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswerMapper {
    Answer getAnswerByFormIdUserName(@Param("form_id") int form_id, @Param("user_name")int user_name);
    boolean addAnswer(Answer answer);
    boolean updateAnswer(Answer answer);
    boolean deleteAnswerByFormId(int id);
    boolean addAnswerAll(@Param("form_id") int form_id,@Param("user_name")String user_name);
    List<Answer> getUserFormList(@Param("user_name")String user_name, @Param("begin_num") int begin_num, @Param("page_size") int page_size);
    List<Answer> getAnswerListByFormId(@Param("form_id") int form_id,@Param("begin_num") int begin_num, @Param("page_size") int page_size);
    List<Answer> getAllAnswerByFormId(@Param("form_id") int form_id);
    List<Answer> searchAnswer(@Param("user_name") String user_name,@Param("begin_num") int begin_num, @Param("page_size") int page_size);
    List<Answer> searchAllAnswerByName(@Param("user_name") String user_name);
}
