package com.miscorf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    int id;
    String user_name;
    String form_name;
    Timestamp deadline;
    int form_id;
    Timestamp answer_time;
    String answer_content;
    boolean answer_type;
    String form_creator;
    String data;
}
