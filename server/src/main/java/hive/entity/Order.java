package hive.entity;

import lombok.Data;

/**
 * @program: hive
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/08
 **/
@Data
public class Order {
    private String order_id;
    private String  buyer_account_id;
    private String good_name;
    private String size;
    private String produce_place;
    private int amount;
    private int year;
    private int month;
    private int day;
    private double  price;
    private String receive_address;
}
