package com.miscorf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Template {
    int id;
    String  name;
    Timestamp creat_time;
    String creator;
    String data;
    boolean visible;
}
