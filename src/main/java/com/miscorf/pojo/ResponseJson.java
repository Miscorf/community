package com.miscorf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.StringHolder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseJson {
    int code =20000;
    String status = "success";
    String roles = "admin";
    Object data;
    String token;
}
