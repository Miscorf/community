package com.miscorf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayItem {
        int pay_item_id;
        String pay_item_name;
        double pay_item_money;
        String pay_item_type;
        Timestamp update_time;
        String pay_item_content;
        int pay_table_items_id;
        int  pay_table_id;
        String creator;

}
