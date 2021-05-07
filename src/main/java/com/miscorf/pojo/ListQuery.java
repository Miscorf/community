package com.miscorf.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListQuery {
    int id;
    int page;
    int limit;
    int importance;
    String title;
    String type;
    String sort;
    String user_name;
}
