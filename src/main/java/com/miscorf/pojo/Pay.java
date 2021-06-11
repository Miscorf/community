package com.miscorf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pay {
    int pay_id;
    int pay_table_id;
    String user_id_name;
    String pay_title;
    String pay_content;
    String user_name;
    int pay_money;
    boolean is_pay;
    List<User> pay_user;
    List<PayItem> PayItems;
    String pay_creator;
    Timestamp deadline;
    Timestamp create_time;
    Timestamp update_time;
    String creatType;

}
