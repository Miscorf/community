package com.miscorf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    int notice_id;
    String notice_title;
    String notice_content;
    Timestamp notice_time;
    String notice_creator;
    int notice_display;
    String notice_right;

    String notice_short_content;
    String notice_image_uri;
    Timestamp notice_display_time;
    boolean notice_comment_disabled;
    String notice_statue;
}
