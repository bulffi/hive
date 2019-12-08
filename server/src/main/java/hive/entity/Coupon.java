package hive.entity;

import lombok.Data;

/**
 * @program: hive
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/08
 **/
@Data
public class Coupon {
    private String coupon_id;
    private double discount;
    private int inventory;
}
