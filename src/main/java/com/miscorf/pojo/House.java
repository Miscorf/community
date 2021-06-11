package com.miscorf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {
    int  house_id;
    String house_address;
    String house_host;
    String house_area;
    String house_type;
    String house_user_name;
    Timestamp house_update_time;
    String user_phone;

}
