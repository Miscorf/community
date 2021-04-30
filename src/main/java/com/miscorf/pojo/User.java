package com.miscorf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    int user_id;
    String user_name;
    String user_gender;
    String user_phone;
    String user_password;
    String user_email;
    int user_house_id;
    String user_right;
    String token;
}
