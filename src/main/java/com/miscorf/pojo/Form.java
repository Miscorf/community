package com.miscorf.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Form {
    int form_id ;
    String form_name;
    int template_id;
    Timestamp form_creat_time;
    Timestamp deadline;
    String form_creator;
    Object type;
    List<User> users;
    String creatType;

}
