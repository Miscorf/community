package com.miscorf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

   int message_id;
   String user_name;
   String message_title;
   String  message_content;
   Timestamp message_date;
   String message_type;
   boolean message_is_read;

}
