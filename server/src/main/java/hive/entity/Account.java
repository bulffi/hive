package hive.entity;

import lombok.Data;

/**
 * @program: hive
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/08
 **/
@Data
public class Account {
    private String account_id;
    private String password;
    private String  nickname;
    private int gender;
    private String description;
}
