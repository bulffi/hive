package hive.entity;

import lombok.Data;

/**
 * @program: hive
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/08
 **/
@Data
public class ReceiveInfo {
    private String receiver_account_id;
    private String receive_info_id;
    private String phone_number;
    private String address;
}
